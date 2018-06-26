import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-operator-form-component',
  templateUrl: './operator-form-component.component.html',
  styleUrls: ['./operator-form-component.component.scss']
})
export class OperatorFormComponentComponent implements OnInit {
  display: boolean=false;

  constructor() { }

  ngOnInit() {
  }
  showDialog() {
    this.display = true;

}
}
