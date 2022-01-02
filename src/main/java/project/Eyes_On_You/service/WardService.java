package project.Eyes_On_You.service;

import org.springframework.stereotype.Service;
import project.Eyes_On_You.domain.entity.Ward;
import project.Eyes_On_You.domain.repository.WardRepository;
import project.Eyes_On_You.dto.WardDto;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class WardService {

    private WardRepository wardRepository;

    public WardService(WardRepository wardRepository){
        this.wardRepository=wardRepository;
    }

    @Transactional
    public Long saveWard(WardDto wardDto){
        System.out.println(wardDto.isDetected());
        return wardRepository.save(wardDto.toEntity()).getId();
    }

    public List<WardDto> getAllWard() {
        List<Ward> wardList = wardRepository.findAll();
        List<WardDto> wardDtoList = new ArrayList<>();

        for(Ward ward : wardList) {
            WardDto wardDto = WardDto.builder()
                    .id(ward.getId())
                    .name(ward.getName())
                    .phoneNum(ward.getPhoneNum())
                    .lastSeen(ward.getLastSeen())
                    .description(ward.getDescription())
                    .fileLoc(ward.getFileLoc())
                    .fileName(ward.getFileName())
                    .detected(ward.isDetected())
                    .build();
            wardDtoList.add(wardDto);
        }
        return wardDtoList;
    }

    public List<WardDto> findDetected(){
        List<Ward> wardList = wardRepository.findByDetectedTrue();
        List<WardDto> wardDtoList = new ArrayList<>();

        for(Ward ward : wardList) {
            WardDto wardDto = WardDto.builder()
                    .id(ward.getId())
                    .name(ward.getName())
                    .phoneNum(ward.getPhoneNum())
                    .lastSeen(ward.getLastSeen())
                    .description(ward.getDescription())
                    .fileLoc(ward.getFileLoc())
                    .fileName(ward.getFileName())
                    .detected(ward.isDetected())
                    .build();
            wardDtoList.add(wardDto);
        }
        return wardDtoList;
    }

    public WardDto getWard(Long id) {
        Ward ward = wardRepository.findById(id).get();

        WardDto wardDto = WardDto.builder()
                .id(ward.getId())
                .name(ward.getName())
                .phoneNum(ward.getPhoneNum())
                .lastSeen(ward.getLastSeen())
                .description(ward.getDescription())
                .fileLoc(ward.getFileLoc())
                .fileName(ward.getFileName())
                .detected(ward.isDetected())
                .build();
        return wardDto;
    }

    public void deletePost(Long id) {
        wardRepository.deleteById(id);
    }
}
