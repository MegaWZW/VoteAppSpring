package com.course.app.dto;

public class GenreWithPointsDTO {
	private String name;
	private Integer points;

	public GenreWithPointsDTO(String name, Integer points) {
		this.name = name;
		this.points = points;
	}

	public GenreWithPointsDTO() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}
}
