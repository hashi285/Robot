package org.example.robot.controller;

import lombok.AllArgsConstructor;
import org.example.robot.service.AdService;
import org.example.robot.service.FifaService;
import org.example.robot.service.AsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class FifaController {

    private final FifaService fifaService;
    private final AsService as_Service;
    private final AdService ad_Service;



    @GetMapping("/fifa")
    public String fifa(Model model) {
        // fifaService의 작업 실행 후 결과를 모델에 추가
        return "main";  // 템플릿 이름 ("/main" -> "main"으로 수정)
    }

    @PostMapping("/fifa")
    public String fifaSubmit(Model model) {
        String result = fifaService.executeTask();
        model.addAttribute("result", result);
        as_Service.B();
        ad_Service.A();
        return "main";
    }
}