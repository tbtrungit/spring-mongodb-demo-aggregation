package com.baotrung.springbootdemomongodb.repository;

import com.baotrung.springbootdemomongodb.dto.EmployeeDto;
import com.baotrung.springbootdemomongodb.dto.EmployeeResult;
import com.baotrung.springbootdemomongodb.utils.Constant;
import com.mongodb.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

public class EmployeeRepositoryCustomImpl implements EmployeeRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public List<String> fetchAllLastNameByLocation(String location) {
        Criteria criteria = Criteria.where(Constant.LOCATION).is(location);
        Aggregation aggregation = newAggregation(
                Aggregation.match(criteria),
                Aggregation.group("lastName").first("lastName").as("lastName")
        );
        AggregationResults<EmployeeDto> results = this.mongoTemplate.aggregate(aggregation, "employees", EmployeeDto.class);
        List<String> lastNames = new ArrayList<>();
        for (EmployeeDto employee : results.getMappedResults()) {
            lastNames.add(employee.getLastName());
        }
        return lastNames;

    }

    @Override
    public Set<String> fetchAllLastNameByLocationUsedCursor(String location) {
        BasicDBObject query = new BasicDBObject("location", location);
        MongoClient mongoClient = new MongoClient(new ServerAddress("localhost", 27017));
        DB db = mongoClient.getDB("BaoTrung");

        DBCollection coll = db.getCollection("employees");

        Set<String> lastNames = new HashSet<>();

        Cursor cursor = coll.find(query);

        while (cursor.hasNext()) {
            DBObject instance = cursor.next();
            lastNames.add(((String) instance.get("lastName")));
        }

        return lastNames;
    }


    @Override
    public List<EmployeeResult> countTotalUserByLocation(String location) {
        Criteria criteria = Criteria.where(Constant.LOCATION).is(location);
        AggregationOperation match = Aggregation.match(criteria);
        AggregationOperation group = Aggregation.group("location").count().as("total");
        Aggregation aggregation = newAggregation(match, group);
        AggregationResults<EmployeeResult> groupResults
                = mongoTemplate.aggregate(aggregation, "Employees", EmployeeResult.class);
        return groupResults.getMappedResults();

    }

    @Override
    public List<EmployeeDto> calculateSalaryByAgeAndLocation(int firstAge, String location) {
        Criteria criteria = Criteria.where("age").gte(firstAge).and("location").is(location);
        AggregationOperation match = Aggregation.match(criteria);
        AggregationOperation group = Aggregation.group("location").sum("salary").as("totalSalary");
//                .push("lastName").as("lastName");
        Aggregation aggregation = newAggregation(match, group);
        AggregationResults<EmployeeDto> groupResults
                = mongoTemplate.aggregate(aggregation, "Employees", EmployeeDto.class);
        return groupResults.getMappedResults();
    }
}
