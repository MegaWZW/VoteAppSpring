package com.course.app.dto;

public class ArtistWithPointsDTO {
	private String name;
	private Integer points;

	public ArtistWithPointsDTO(String name, Integer points) {
		this.name = name;
		this.points = points;
	}

	public ArtistWithPointsDTO(){

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
