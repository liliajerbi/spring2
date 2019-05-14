package com.example.spring.challenge2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import javax.print.Doc;
import java.util.ArrayList;

@Controller
@SpringBootApplication
public class Challenge2Application {

    public static void main(String[] args) {
        SpringApplication.run(Challenge2Application.class, args);
    }

    @RequestMapping("/doctor/{idInc}")
    @ResponseBody
    public Doctor myDoctor(@PathVariable int idInc, @RequestParam(required = false) boolean details) {

        Doctor christopher = new Doctor("9", "Christopher");
        Doctor david = new Doctor("10", "David");
        Doctor matt = new Doctor("11", "Matt");
        Doctor peter = new Doctor("12", "Peter");
        Doctor jodie = new Doctor("13", "Jodie");

        ArrayList <Doctor> doctorList = new ArrayList<>();
        doctorList.add(christopher);
        doctorList.add(david);
        doctorList.add(matt);
        doctorList.add(peter);
        doctorList.add(jodie);

        ExtendDoctor extendChristopher = new ExtendDoctor("9", "Christopher", "13","41");
        ExtendDoctor extendDavid = new ExtendDoctor("10", "David", "47","34");
        ExtendDoctor extendMatt = new ExtendDoctor("11", "Matt", "44","27");
        ExtendDoctor extendPeter = new ExtendDoctor("12", "Peter", "40","55");
        ExtendDoctor extendJodie = new ExtendDoctor("13", "Jodie", "11","35");

        ArrayList<ExtendDoctor> extendDoctorsList = new ArrayList<>();
        extendDoctorsList.add(extendChristopher);
        extendDoctorsList.add(extendDavid);
        extendDoctorsList.add(extendMatt);
        extendDoctorsList.add(extendPeter);
        extendDoctorsList.add(extendJodie);


        if ( (idInc >= 14) || (idInc <= 0)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Impossible de récupérer l'incarnation " + idInc);
        }
        if ( (idInc == 1) || (idInc == 2) || (idInc == 3) || (idInc == 4) || (idInc == 5) || (idInc == 6) || (idInc == 7) || (idInc == 8)) {
            throw new ResponseStatusException(HttpStatus.SEE_OTHER, "See Other");
        }
        if (details){
            return extendDoctorsList.get(idInc - 9);
        }

        return doctorList.get(idInc - 9);
    }

    class Doctor {

        private String number;
        private String name;

        public Doctor(String number, String name) {
            this.number = number;
            this.name = name;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    class ExtendDoctor extends Doctor {

        private String nbEpisode;

        public ExtendDoctor(String number, String name, String nbEpisode, String ageStart) {
            super(number, name);
            this.nbEpisode = nbEpisode;
            this.ageStart = ageStart;
        }

        private String ageStart;

        public String getNbEpisode() {
            return nbEpisode;
        }

        public void setNbEpisode(String nbEpisode) {
            this.nbEpisode = nbEpisode;
        }

        public String getAgeStart() {
            return ageStart;
        }

        public void setAgeStart(String ageStart) {
            this.ageStart = ageStart;
        }

        public ExtendDoctor(String number, String name) {
            super(number, name);
        }
    }
}
