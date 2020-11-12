import { StudentsService } from './../services/students.service';
import { Component, OnInit } from '@angular/core';
import { Student } from '../models/student';
import { delay } from 'rxjs/operators';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.css']
})
export class StudentListComponent implements OnInit {


  public students: Student[];

  public showSpinner: boolean;

  constructor(private studentsService: StudentsService) { }

  ngOnInit(): void {
    this.showSpinner = true;
    this.studentsService.getStudentsList()
    .pipe(delay(2000))
    .subscribe((studentsResponse => {
      this.students = studentsResponse;
      this.showSpinner = false;
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
