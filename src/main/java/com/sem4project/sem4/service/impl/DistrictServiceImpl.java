package com.sem4project.sem4.service.impl;
import com.sem4project.sem4.dto.dtomodel.DistrictDto;
import com.sem4project.sem4.dto.dtomodel.ProvinceDto;
import com.sem4project.sem4.entity.District;
import com.sem4project.sem4.entity.Province;
import com.sem4project.sem4.exception.CRUDException;
import com.sem4project.sem4.mapper.DistrictMapper;
import com.sem4project.sem4.repository.DistrictRepository;
import com.sem4project.sem4.repository.ProvinceRepository;
import com.sem4project.sem4.service.DistrictService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class DistrictServiceImpl implements DistrictService {
    private final DistrictRepository districtRepository;
    private final ProvinceRepository provinceRepository;

    @Override
    public void create(DistrictDto districtDto) {
        try {
            Province province = provinceRepository.findById(districtDto.getProvince().getId()).orElse(new Province());
            District district = DistrictMapper.districtFromDistrictDto(districtDto);
            district.setProvince(province);
            districtRepository.save(district);
        } catch (Exception e) {
            throw new CRUDException("Create fail");
        }
    }

    @Override
    public List<DistrictDto> getAllDistrict(){
        try {
            List<District> districts = districtRepository.findAll();
            return districts.stream().map(DistrictMapper::districtDtoFromDistrict).toList();
        }catch (Exception e){
            throw new CRUDException("Get all fail");
        }
    }

    @Override
    public DistrictDto getDistrict(Long id) {
        try {
           District district = districtRepository.findById(id).orElseThrow(() -> new CRUDException("id" +id + "not found"));
            return DistrictMapper.districtDtoFromDistrict(district);
        }catch (Exception e){
            throw new CRUDException("Cant find District with id = "  +  id);
        }
    }

    @Override
    public DistrictDto updateDistrict(DistrictDto districtDto) {
        try {
            District district = districtRepository.findById(districtDto.getId()).orElseThrow(() -> new CRUDException("id" +districtDto.getId() + "not found"));
            DistrictMapper.transferDistrictDtoToDistrict(districtDto, district);
            districtRepository.save(district);
            return districtDto;
        }catch (Exception e){
            throw new CRUDException("Cant find District with id = "  +  districtDto.getId());
        }
    }

    @Override
    public void deleteDistrict(DistrictDto districtDto) {
        try {
            District district = districtRepository.findById(districtDto.getId()).orElseThrow(() -> new CRUDException("id" +districtDto.getId() + "not found"));
            district.setDisable(true);
            districtRepository.save(district);
        }catch (Exception e){
            throw new CRUDException("Cant find District with id = "  +  districtDto.getId());
        }
    }

    @Override
    public void transferCommuneToProvince(DistrictDto districtDto, ProvinceDto provinceDto) {
        District district = districtRepository.findById(districtDto.getId()).orElseThrow(() -> new CRUDException("District with id = " +districtDto.getId() +" not found"));
        Province province = provinceRepository.findById(provinceDto.getId()).orElseThrow(() -> new CRUDException("Province with id = " +provinceDto.getId() +" not found"));
        district.setProvince(province);
        districtRepository.save(district);
    }
}
