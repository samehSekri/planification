import { Component, OnInit, ViewChild, ElementRef, AfterViewInit, ViewEncapsulation } from '@angular/core';
import * as $ from 'jquery';
import { DashboardService } from "app/pages/dashboard/dashboard.service";
import { AuthenticationService } from "app/authentication";
import { TranslateService } from "@ngx-translate/core";
import { Observable } from "rxjs/Rx";

@Component({
  selector: 'dashboard',
  styleUrls: ['./dashboard.scss'],
  templateUrl: './dashboard.html',
  encapsulation: ViewEncapsulation.None
})
export class Dashboard implements OnInit {

  ngOnInit() {
  }

}
