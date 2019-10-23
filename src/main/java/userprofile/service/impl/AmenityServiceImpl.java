package userprofile.service.impl;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import userprofile.model.Amenity;
import userprofile.service.AmenityService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AmenityServiceImpl implements AmenityService {

    @Autowired
    SQLContext sqlContext;

    private final StructType schema = new StructType(new StructField[]{
            new StructField("timestamp", DataTypes.StringType, false, Metadata.empty()),
            new StructField("userId", DataTypes.StringType, false, Metadata.empty()),
            new StructField("amenityId", DataTypes.LongType, false, Metadata.empty())
    });

    @Override
    public List<Amenity> getTopAmenityByUserId(String userId, int size) {
        Dataset<Row> df = sqlContext.read().format("csv").schema(schema).load("selections.csv");
        df.createOrReplaceTempView("amenity");
        StringBuilder queryBuilder = new StringBuilder("select amenityId, count(amenityId) amenityCount from amenity where userId =")
                .append(userId)
                .append(" group by amenityId")
                .append(" order by amenityCount")
                .append(" desc");
        Dataset<Row> res = sqlContext.sql(queryBuilder.toString());
        return res.limit(size).collectAsList().stream().map(this::rowAmenityFunction).collect(Collectors.toList());
    }

    private Amenity rowAmenityFunction(Row row) {
        Amenity amenity = new Amenity();
        amenity.setAmenityId(row.getLong(0));
        amenity.setAmenityCount(row.getLong(1));
        return amenity;
    }
}
