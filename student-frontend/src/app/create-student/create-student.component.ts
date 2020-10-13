import { StudentsService } from './../services/students.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Student } from '../models/student';
import { Router } from '@angular/router';
import { Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-create-student',
  templateUrl: './create-student.component.html',
  styleUrls: ['./create-student.component.css']
})
export class CreateStudentComponent implements OnInit {

  createForm: FormGroup;

  @Output() createEvent = new EventEmitter<Student>();

  constructor(public formBuilder: FormBuilder,
              private studentsService: StudentsService) { }

  ngOnInit(): void {
    this.createForm = this.formBuilder.group({
      name: '',
      firstName: ''
    });
  }

  onSubmit(studentData): void{
    this.createForm.reset();
    const student: Student =  {
      name : studentData.name,
      firstName: studentData.firstName
    };
    this.studentsService.createStudent(student).subscribe((studentResponse) => {
      this.createEvent.emit(studentResponse);
    });
  }

}
