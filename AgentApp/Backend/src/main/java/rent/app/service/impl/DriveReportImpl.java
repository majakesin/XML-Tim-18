package rent.app.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rent.app.dto.DriveReportDto;
import rent.app.model.Car;
import rent.app.model.DriveReport;
import rent.app.repository.CarRepository;
import rent.app.repository.DriveReportRepository;
import rent.app.service.DriveReportService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DriveReportImpl implements DriveReportService {

    private final DriveReportRepository driveReportRepository;
    private final CarRepository carRepository;

    @Override
    public List<DriveReportDto> getAllReports() {
        List<DriveReport> lista = this.driveReportRepository.findAll();
        List<DriveReportDto> listaDto= new ArrayList<>();

        for (DriveReport d: lista) {
            DriveReportDto report = new DriveReportDto();
            report.setId(d.getId());
            report.setTraveledDistance(d.getTraveledDistance());
            report.setDateOfReport(d.getDateOfReport());
            report.setDescribe(d.getDescribe());
    //        report.setCarId(d.getCarReport().getId());

            listaDto.add(report);
        }
        return listaDto;
    }

    @Override
    public void addNewReport(DriveReportDto newReport) {

        DriveReport report = new DriveReport();
     //   Car car = this.carRepository.findAllById(newReport.getCarId());
        report.setId(newReport.getId());
     //   report.setCarReport(car);
        report.setDateOfReport(newReport.getDateOfReport());
        report.setDescribe(newReport.getDescribe());
        report.setTraveledDistance(newReport.getTraveledDistance());
        driveReportRepository.save(report);

    }

    @Override
    public List<DriveReportDto> getAllReportsByCarId(Long carId) {
        List<DriveReport> lista = this.driveReportRepository.findAllByCarReportId(carId);
        List<DriveReportDto> listaDto= new ArrayList<>();

        for (DriveReport d: lista) {
            DriveReportDto report = new DriveReportDto();
            report.setId(d.getId());
            report.setTraveledDistance(d.getTraveledDistance());
            report.setDateOfReport(d.getDateOfReport());
            report.setDescribe(d.getDescribe());
            //        report.setCarId(d.getCarReport().getId());

            listaDto.add(report);
        }
        return listaDto;
    }
}
