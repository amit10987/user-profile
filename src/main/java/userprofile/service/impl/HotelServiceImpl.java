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
import userprofile.model.Hotel;
import userprofile.service.HotelService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    SQLContext sqlContext;

    private final StructType schema = new StructType(new StructField[]{
            new StructField("timestamp", DataTypes.StringType, false, Metadata.empty()),
            new StructField("userId", DataTypes.StringType, false, Metadata.empty()),
            new StructField("hotelId", DataTypes.LongType, false, Metadata.empty()),
            new StructField("hotelRegion", DataTypes.StringType, false, Metadata.empty())
    });

    @Override
    public List<Hotel> getTopHotelsByUserId(String userId, int size) {
        Dataset<Row> df = sqlContext.read().format("csv").schema(schema).load("clicks.csv");
        df.createOrReplaceTempView("hotel");
        StringBuilder queryBuilder = new StringBuilder("select hotelId, count(hotelId) clickCount from hotel where userId =")
                .append(userId)
                .append(" group by hotelId")
                .append(" order by clickCount")
                .append(" desc");
        Dataset<Row> res = sqlContext.sql(queryBuilder.toString());
        return res.limit(size).collectAsList().stream().map(this::rowHotelFunction).collect(Collectors.toList());
    }

    private Hotel rowHotelFunction(Row row) {
        Hotel hotel = new Hotel();
        hotel.setHotelId(row.getLong(0));
        hotel.setClickCount(row.getLong(1));
        return hotel;
    }
}
