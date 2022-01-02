package project.Eyes_On_You.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.Eyes_On_You.dto.WardDto;
import project.Eyes_On_You.service.WardService;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping
public class WardController {

    private WardService wardService;

    public WardController(WardService wardService){
        this.wardService=wardService;
    }

    @GetMapping("/wards")
    public String wards(Model model){
        List<WardDto> wards= wardService.getAllWard();
        model.addAttribute("wards", wards);
        return "/wards.html";
    }

    @GetMapping("/wards/{wardId}")
    public String ward(@PathVariable("wardId") long wardId, Model model){
        WardDto ward = wardService.getWard(wardId);
        model.addAttribute("ward",ward);
        return "/ward.html";
    }

    @GetMapping("/wards/add")
    public String addForm(){
        return "/addForm.html";
    }

    @PostMapping("/wards/add")
    public String add(WardDto wardDto,
                      @RequestParam("image") MultipartFile image){
        String fileLoc = "C:\\Users\\race9\\Desktop\\프로젝트\\project\\project\\src\\main\\resources\\static\\images\\"+image.getOriginalFilename();
        try{
            File file =new File(fileLoc);
            image.transferTo(file);
        }
        catch(IllegalStateException | IOException e){
            e.printStackTrace();
        }
        wardDto.setLastSeen(LocalDateTime.now());
        wardDto.setFileLoc(fileLoc);
        wardDto.setDetected(true);
        wardDto.setFileName(image.getOriginalFilename());
        wardService.saveWard(wardDto);
        return "redirect:/wards";
    }

    @GetMapping("/wards/edit/{wardId}")
    public String edit(@PathVariable("wardId") long wardId, Model model){
        WardDto wardDto = wardService.getWard(wardId);
        model.addAttribute("ward",wardDto);
        return "/editForm.html";
    }

    @PostMapping("/wards/edit/{wardId}")
    public String update(@RequestParam("wardId") long wardId,
                         @RequestParam("name") String name,
                         @RequestParam("phoneNum") String phoneNum,
                         @RequestParam("description") String description) {
        WardDto wardDto = wardService.getWard(wardId);
        System.out.println(wardDto.toString());
        wardDto.setName(name);
        wardDto.setPhoneNum(phoneNum);
        wardDto.setLastSeen(LocalDateTime.now());
        wardDto.setDescription(description);
        System.out.println(wardDto.toString());
        wardService.saveWard(wardDto);
        return "redirect:/wards/{wardId}";
    }


    @GetMapping("/monitor")
    public String monitor(Model model){
//        List<WardDto> wards= wardService.getAllWard();
        List<WardDto> wards = wardService.findDetected();
        model.addAttribute("wards", wards);
        return "/index2.html";
    }
}
