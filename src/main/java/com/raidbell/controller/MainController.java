package com.raidbell.controller;

import com.raidbell.domain.Discipline;
import com.raidbell.domain.Exam;
import com.raidbell.domain.Student;
import com.raidbell.repository.DisciplineRepository;
import com.raidbell.repository.ExamRepository;
import com.raidbell.repository.StudentRepository;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Controller
public class MainController {

    private final Logger logger = Logger.getLogger(MainController.class);
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private DisciplineRepository disciplineRepository;

    @ResponseBody
    @GetMapping(value = "/")
    public String main() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("count", examRepository.countSuccessStudents(4.6F));

        return jsonObject.toString();
    }

    @GetMapping(value = "/init")
    public String initialization() {

        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Student student = new Student();
            student.setName("name" + i);
            student.setLastName("lastName" + i);
            student.setPatronymic("patronymic" + i);
            studentList.add(student);
        }
        studentList = studentRepository.saveAll(studentList);

        List<Discipline> disciplineList = new ArrayList<>();
        List<String> disciplineName = Arrays.asList("English", "Russian", "Java");
        for (String name : disciplineName) {
            Discipline discipline = new Discipline();
            discipline.setName(name);
            disciplineList.add(discipline);
        }
        disciplineList = disciplineRepository.saveAll(disciplineList);
        final Random random = new Random();
        List<Exam> examList = new ArrayList<>();

        for (Student student : studentList) {
            for (Discipline discipline : disciplineList) {
                Exam exam = new Exam();
                exam.setStudent(student);
                exam.setDiscipline(discipline);
                exam.setAssessment(random.nextInt(4) + 2);
                examList.add(exam);
            }
        }
        examRepository.saveAll(examList);


        return "redirect:/";
    }

}
