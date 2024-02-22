package subway.line.load;

import org.springframework.stereotype.Service;
import subway.line.Line;
import subway.line.LineRepository;
import subway.section.SectionRepository;
import subway.section.Sections;
import subway.station.Station;
import subway.station.StationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LineLoadService {

    private final LineRepository lineRepository;
    private final StationRepository stationRepository;
    private final SectionRepository sectionRepository;

    public LineLoadService(LineRepository lineRepository, StationRepository stationRepository, SectionRepository sectionRepository) {
        this.lineRepository = lineRepository;
        this.stationRepository = stationRepository;
        this.sectionRepository = sectionRepository;
    }

    public List<LineLoadedResponse> loadLines() {
        List<Line> lines = lineRepository.findAll();

        return lines.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        }

    public LineLoadedResponse loadLine(Long lineId) {
        Line line = lineRepository.findById(lineId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 노선입니다. lineId: " + lineId));

        return mapToResponse(line);
    }

    private LineLoadedResponse mapToResponse(Line line) {
        Sections sections = new Sections(sectionRepository.findAllByLineIdOrderById(line.getId()));
        List<Station> stations = sections.getAllStations();
        return new LineLoadedResponse(line.getId(), line.getName(), line.getColor(), mapToStationResponses(stations));
    }

    private List<LineLoadedStationResponse> mapToStationResponses(List<Station> stations) {
        return stations.stream()
                .map(station -> new LineLoadedStationResponse(station.getId(), station.getName()))
                .collect(Collectors.toList());
    }
}
