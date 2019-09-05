package com.scale.global.insurance.app.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDTO {

    @ApiModelProperty(value = "This is the insurance number", position = 1)
    private Integer insuranceNumber;

    @ApiModelProperty(value = "This is the first name", required = true, example = "Marsellus", position = 2)
    @Size(min=2, max=100, message = "Min size is 2 and max size is 100")
    private String firstName;

    @ApiModelProperty(value = "This is the last name", required = true, example = "Wallace", position = 3)
    @Size(min=2, max=100, message = "Min size is 2 and max size is 100")
    private String lastName;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @ApiModelProperty(value = "This is the date of birth", required = true, example = "16-01-1989", position = 4)
    @PastOrPresent(message = "Date should be in past or to be present date")
    private LocalDate dateOfBirth;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @ApiModelProperty(value = "This is the inception date", required = true, example = "23-04-2017", position = 5)
    @PastOrPresent(message = "Date should be in past or to be present date")
    private LocalDate inceptionOfThePolicy;

    @ApiModelProperty(value = "This is customer's monthly rate", readOnly = true, position = 6)
    private BigDecimal rate;

}