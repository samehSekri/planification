import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-cadence-client',
  templateUrl: './cadence-client.component.html',
  styleUrls: ['./cadence-client.component.scss']
})
export class CadenceClientComponent implements OnInit {
  display: boolean;

  constructor() { }
  public showDialog() {
    this.display = true;
   
  }
  ngOnInit() {
  }

}
