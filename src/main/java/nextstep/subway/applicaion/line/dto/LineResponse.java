package nextstep.subway.applicaion.line.dto;

import nextstep.subway.applicaion.station.domain.Station;
import nextstep.subway.applicaion.station.dto.StationResponse;

import java.util.List;

public class LineResponse {

	private Long id;
	private String name;
	private String color;
	private List<StationResponse> stations;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getColor() {
		return color;
	}

	public List<StationResponse> getStations() {
		return stations;
	}
	public LineResponse(Long id, String name, String color, List<StationResponse> stations) {
		this.id = id;
		this.name = name;
		this.color = color;
		this.stations = stations;
	}
}
