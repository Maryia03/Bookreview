package com.example.bookreview.models.DTO;

import lombok.Getter;
import java.math.BigDecimal;

@Getter
public class RatingRequest{
    private BigDecimal score;
}