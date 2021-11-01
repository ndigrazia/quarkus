package tech.donau.datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.agroal.api.AgroalDataSource;
import io.quarkus.agroal.DataSource;

@Path("/hello")
public class GreetingResource {

    @Inject
    @DataSource("hello")
    AgroalDataSource dataSource;
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() throws SQLException {
        PreparedStatement prepareStatement = dataSource.getConnection().prepareStatement("select * from greeting");
        prepareStatement.executeQuery();
        ResultSet resultSet = prepareStatement.getResultSet();
        resultSet.next();
        return resultSet.getString(2);
    }
}