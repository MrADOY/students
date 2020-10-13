import { StudentsService } from './../services/students.service';
import { Component, OnInit } from '@angular/core';
import { Student } from '../models/student';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.css']
})
export class StudentListComponent implements OnInit {

  public students: Student[];

  constructor(private studentsService: StudentsService) { }

  ngOnInit(): void{
    this.studentsService.getStudentsList().subscribe((studentsResponse => {
      this.students = studentsResponse;
    }));
  }

  deleteStudent(id: number): void {
    this.studentsService.deleteStudent(id).subscribe((deleteResponse) => {
      this.students = this.students.filter(s => s.id !== id);
    }, (error) => {
      // TODO
    });
  }
  /* Callback of child component */
  addStudent(newStudent: Student): void {
    this.students.push(newStudent);
  }

}
