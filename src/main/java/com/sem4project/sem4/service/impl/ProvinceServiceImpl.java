package com.sem4project.sem4.service.impl;
import com.sem4project.sem4.dto.dtomodel.ProvinceDto;
import com.sem4project.sem4.entity.Province;
import com.sem4project.sem4.exception.CRUDException;
import com.sem4project.sem4.mapper.DistrictMapper;
import com.sem4project.sem4.mapper.ProvinceMapper;
import com.sem4project.sem4.repository.ProvinceRepository;
import com.sem4project.sem4.service.ProvinceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@AllArgsConstructor
@Service
public class ProvinceServiceImpl implements ProvinceService {

    private final ProvinceRepository provinceRepository;

    @Override
    public void create(ProvinceDto provinceDto) {
        try {
            Province province = ProvinceMapper.provinceFromProvinceDto(provinceDto);
            provinceRepository.save(province);
        } catch (Exception e) {
            throw new CRUDException("can not create new province");
        }
    }

    @Override
    public List<ProvinceDto> getAllProvince() {
        try {
            List<Province> provinces = provinceRepository.findAll();
            return provinces.stream().map(province -> {
                ProvinceDto provinceDto = ProvinceMapper.provinceDtoFromProvince(province);
                provinceDto.setDistricts(province.getDistricts().stream().map(DistrictMapper::districtDtoFromDistrict).toList());
                return provinceDto;
            }).toList();
        } catch (Exception e) {
            throw new CRUDException("Get all fail !!");
        }
    }

    @Override
    public ProvinceDto getProvince(Long id) {
        try {
            Province province = provinceRepository.findById(id).orElseThrow(() -> new CRUDException("Cant find Province with id = "  +  id));
            ProvinceDto provinceDto = ProvinceMapper.provinceDtoFromProvince(province);
            provinceDto.setDistricts(province.getDistricts().stream().map(DistrictMapper::districtDtoFromDistrict).toList());
            return provinceDto;
        } catch (Exception e) {
            throw new CRUDException("Cant find Province with id = "  +  id);
        }
    }

    @Override
    public ProvinceDto updateProvince(ProvinceDto provinceDto) {
        try {
            Province province = provinceRepository.findById(provinceDto.getId()).orElseThrow(() -> new CRUDException("id = " + provinceDto.getId() + " not found "));
            ProvinceMapper.transferProvinceDtoToProvince(provinceDto, province);
            provinceRepository.save(province);
            return provinceDto;
        }
        catch (Exception e){
            throw new CRUDException("Cant find Province with id = "  +  provinceDto.getId());
        }
    }

    @Override
    public void setDisableProvince(ProvinceDto provinceDto,boolean isDisable) {
        try {
            Province province = provinceRepository.findById(provinceDto.getId()).orElseThrow(() -> new CRUDException("id = " + provinceDto.getId() + " not found "));
            province.setDisable(provinceDto.isDisable());
            provinceRepository.save(province);
        }
        catch (Exception e){
            throw new CRUDException("Cant find Province with id = "  +  provinceDto.getId());
        }
    }

    @Override
    public List<ProvinceDto> getAllAvailableProvince() {
        try {
            List<Province> provinces = provinceRepository.findAllByIsDisable(false);
            return provinces.stream().map(province -> {
                ProvinceDto provinceDto = ProvinceMapper.provinceDtoFromProvince(province);
                provinceDto.setDistricts(province.getDistricts().stream().map(DistrictMapper::districtDtoFromDistrict).toList());
                return provinceDto;
            }).toList();
        } catch (Exception e) {
            throw new CRUDException("Get all fail !!");
        }
    }


}
