package com.maksim.spring.samples.dao;

import com.maksim.spring.samples.model.Officer;
import com.maksim.spring.samples.model.Station;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.object.BatchSqlUpdate;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;

public class CRUDJdbcTemplateDao {
    private NamedParameterJdbcTemplate jdbcTemplate;
    private StationMapper allStationMapper;
    private StationMapperById stationMapperById;
    private AllStationWithOfficersExtractor allStationWithOfficersExtractor;
    private InsertStation insertStation;
    private InsertOfficer insertOfficer;
    private UpdateStation updateStation;
    private DeleteStation deleteStation;

    private static String SQL_READ_STATIONS_QUERY = "SELECT * FROM Station";

    private static String SQL_READ_STATION_QUERY = "SELECT * FROM Station st WHERE st.id = :id";

    private static String SQL_READ_STATION_WITH_OFFICER_QUERY =
            "SELECT st.id as station_id, st.name as station_name, st.line as station_line, " +
                    "ofc.id as officer_id, ofc.name as officer_name, ofc.position as officer_position " +
                    "FROM Station st LEFT JOIN Officer ofc ON ofc.station_id = st.id " +
                    "ORDER BY st.id, ofc.id";

    private static String SQL_INSERT_STATION_QUERY = "INSERT INTO Station(name, line) VALUES(:name, :line)";

    private static String SQL_INSERT_OFFICER_QUERY = "INSERT INTO Officer(name, position, station_id) " +
            "VALUES(:name, :position, :station_id)";

    private static String SQL_UPDATE_STATION_QUERY = "UPDATE Station SET name = :name, line = :line WHERE id = :id";

    private static String SQL_DELETE_STATION_QUERY = "DELETE FROM Station WHERE id = :id";

    public CRUDJdbcTemplateDao(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.allStationMapper = new StationMapper(dataSource);
        this.stationMapperById = new StationMapperById(dataSource);
        this.allStationWithOfficersExtractor = new AllStationWithOfficersExtractor();
        this.insertStation = new InsertStation(dataSource);
        this.insertOfficer = new InsertOfficer(dataSource);
        this.updateStation = new UpdateStation(dataSource);
        this.deleteStation = new DeleteStation(dataSource);
    }

    private static class StationMapper extends MappingSqlQuery<Station> {
        public StationMapper(DataSource ds) {
            super(ds, SQL_READ_STATIONS_QUERY);
        }

        protected StationMapper(DataSource ds, String sql) {
            super(ds, sql);
        }

        @Override
        protected Station mapRow(ResultSet resultSet, int i) throws SQLException {
            Station station = new Station();
            station.setId(resultSet.getInt(1));
            station.setName(resultSet.getString(2));
            station.setLine(resultSet.getString(3));
            return station;
        }
    }

    private static class StationMapperById extends StationMapper {
        public StationMapperById(DataSource ds) {
            super(ds, SQL_READ_STATION_QUERY);
            super.declareParameter(new SqlInOutParameter("id", Types.INTEGER));
        }
    }

    private static class AllStationWithOfficersExtractor implements ResultSetExtractor<List<Station>> {
        @Override
        public List<Station> extractData(ResultSet rs) throws SQLException, DataAccessException {
            Map<Integer, Station> stations = new HashMap<Integer, Station>();

            while (rs.next()) {
                int stationId = rs.getInt("station_id");
                Station station;

                if ((station = stations.get(stationId)) == null) {
                    // read station
                    station = new Station();
                    station.setId(stationId);
                    station.setName(rs.getString("station_name"));
                    station.setLine(rs.getString("station_line"));
                    station.setOfficers(new ArrayList<>());

                    stations.put(stationId, station);
                }

                //read officers
                int officerId = rs.getInt("officer_id");

                if (officerId > 0) {
                    Officer officer = new Officer();
                    officer.setId(officerId);
                    officer.setName(rs.getString("officer_name"));
                    officer.setPosition(rs.getString("officer_position"));
                    officer.setStationId(stationId);

                    station.getOfficers().add(officer);
                }
            }

            return new ArrayList<>(stations.values());
        }
    }

    private static class InsertStation extends SqlUpdate {
        public InsertStation(DataSource ds) {
            super(ds, SQL_INSERT_STATION_QUERY);
            super.declareParameter(new SqlInOutParameter("name", Types.VARCHAR));
            super.declareParameter(new SqlInOutParameter("line", Types.VARCHAR));
            super.setGeneratedKeysColumnNames("id");
            super.setReturnGeneratedKeys(true);
        }
    }

    private static class InsertOfficer extends BatchSqlUpdate {
        private static int BATCH_SIZE = 10;

        public InsertOfficer(DataSource ds) {
            super(ds, SQL_INSERT_OFFICER_QUERY);
            super.declareParameter(new SqlInOutParameter("name", Types.VARCHAR));
            super.declareParameter(new SqlInOutParameter("position", Types.VARCHAR));
            super.declareParameter(new SqlInOutParameter("station_id", Types.INTEGER));
            super.setGeneratedKeysColumnNames("id");
            super.setReturnGeneratedKeys(true);

            setBatchSize(BATCH_SIZE);
        }
    }

    private static class UpdateStation extends SqlUpdate {
        public UpdateStation(DataSource ds) {
            super(ds, SQL_UPDATE_STATION_QUERY);
            super.declareParameter(new SqlInOutParameter("id", Types.INTEGER));
            super.declareParameter(new SqlInOutParameter("name", Types.VARCHAR));
            super.declareParameter(new SqlInOutParameter("line", Types.VARCHAR));
        }
    }

    private static class DeleteStation extends SqlUpdate {
        public DeleteStation(DataSource ds) {
            super(ds, SQL_DELETE_STATION_QUERY);
            super.declareParameter(new SqlInOutParameter("id", Types.INTEGER));
        }
    }

    public Station getStationById(int id) {
        Map<String, Object> param = Collections.singletonMap("id", id);
        List<Station> stations = this.stationMapperById.executeByNamedParam(param);
        return !stations.isEmpty() ? stations.get(0) : null;
    }

    public List<Station> getAllStation() {
        return this.allStationMapper.execute();
    }

    public List<Station> getAllStationWithOfficers() {
        return this.jdbcTemplate.query(SQL_READ_STATION_WITH_OFFICER_QUERY, this.allStationWithOfficersExtractor);
    }

    public void insertStation(Station station) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", station.getName());
        params.put("line", station.getLine());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        insertStation.updateByNamedParam(params, keyHolder);
        station.setId(keyHolder.getKey().intValue());
    }

    public void insertStationWithOfficer(Station station) {
        insertStation(station);

        List<Officer> officers = station.getOfficers();
        int stationId = station.getId();

        for (Officer officer : officers) {
            Map<String, Object> params = new HashMap<>();
            params.put("name", officer.getName());
            params.put("position", officer.getPosition());
            params.put("station_id", stationId);
            KeyHolder keyHolder = new GeneratedKeyHolder();
            insertOfficer.updateByNamedParam(params, keyHolder);
            officer.setId(keyHolder.getKey().intValue());
            officer.setStationId(stationId);
        }
        insertOfficer.flush();
    }

    public void updateStation(Station station) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", station.getId());
        params.put("name", station.getName());
        params.put("line", station.getLine());
        updateStation.updateByNamedParam(params);
    }

    public void deleteStation(Station station) {
        Map<String, Object> params = Collections.singletonMap("id", station.getId());
        deleteStation.updateByNamedParam(params);
    }
}
