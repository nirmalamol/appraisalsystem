package com.itorganization.appraisalsystem.constants;

public enum StarRatingPercentEnum {

    FIVE(10),
    FOUR(7),
    THREE(5),
    TWO(0),
    ONE(0),
    ZERO(0);

    int percentHike;

    StarRatingPercentEnum(int percentHike){
        this.percentHike = percentHike;
    }

    public int getPercentHike() {
        return percentHike;
    }
}
