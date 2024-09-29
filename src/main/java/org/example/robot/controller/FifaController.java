package org.example.robot.controller;

import lombok.AllArgsConstructor;
import org.example.robot.enhanceService.이미지_특정위치_숫자_추출;
import org.example.robot.service.AdService;
import org.example.robot.service.FifaService;
import org.example.robot.service.AsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class FifaController {

    private final FifaService fifaService;
    private final AsService as_Service;
    private final AdService ad_Service;
    private final 이미지_특정위치_숫자_추출 추출;



    @GetMapping("/fifa")
    public String fifaSubmit(Model model) {
        String result = fifaService.executeTask();
        model.addAttribute("result", result);
        as_Service.B(); // 선수팩 구매
        ad_Service.A(); // 강화할 선수팩 종류 캡쳐
        추출.A(); // 이미지 전처리 이후 특정 선수팩이 몇강인지 텍스트로 추출 ( 특정 값을 만족하는 경우 다음 메서드 실행 )

        /* 강화 실행 메서드 -------------------------------------------------------------------------------------- */


        return "main";
    }
//    @GetMapping("/fifa")
//    public String fifa(Model model) {
//        // fifaService의 작업 실행 후 결과를 모델에 추가
//        return "main";  // 템플릿 이름 ("/main" -> "main"으로 수정)
//    }
//
//    @PostMapping("/fifa")
//    public String fifaSubmit(Model model) {
//        String result = fifaService.executeTask();
//        model.addAttribute("result", result);
//        as_Service.B();
//        ad_Service.A();
//        return "main";
//    }
}