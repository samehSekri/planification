import {Component, Input} from '@angular/core';

@Component({
  selector: 'ba-card',
  styleUrls: ['./baCard.scss'],
  templateUrl: './baCard.html',
})
export class BaCard {
  @Input() title:String;
  @Input() baCardClass:String;
  @Input() cardType:String;
  @Input() toolbox:boolean;
  @Input() collapseIn:String = "false";

  public isCollapsed: boolean = false;

  ngOnInit(){
    this.isCollapsed = false;
    if(this.collapseIn.toLowerCase() == 'true'){
      this.isCollapsed = true;  
    }
    //
  }
  
  private changeStateClick():void {
    this.isCollapsed = !this.isCollapsed;
  }
}
