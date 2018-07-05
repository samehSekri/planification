import { Component, OnInit, ViewEncapsulation, Input, ViewChild, ElementRef, Renderer } from '@angular/core';

import { OrganizationChartModule } from 'primeng/components/organizationchart/organizationchart';
import { Message, MenuItem, SelectItem, ConfirmationService } from 'primeng/components/common/api';
import { TreeNode } from 'primeng/primeng';
import * as $ from 'jquery';
import { UnitManagementService } from './unit-form.service';
import { TranslateService } from '@ngx-translate/core/src/translate.service';
import { Router } from '@angular/router';
import { UniteTypeEnum } from 'app/shared/model/enumeration/unitetype.enum';
import { Unit } from '../../../../shared/model/unit.model';




@Component({
    selector: 'unit-management',
    providers: [UnitManagementService],
    templateUrl: './unit-management.component.html',
    styleUrls: ['./unit-management.component.scss'],

    encapsulation: ViewEncapsulation.None
})

export class UnitManagementComponent implements OnInit {
   
    currentUap: Unit;

    types: SelectItem[];
    modes: SelectItem[];
    public displayMenu: boolean = false;
    items: MenuItem[];
    data1: TreeNode[];
    display: boolean = false;
    public isEditMode = false;

    data2: TreeNode[];

    selectedNode: TreeNode;
    currentNode: TreeNode;

    messages: Message[];
    currentAtelier: Unit;
    isSaving: boolean;
    public msgs: Message[] = [];
    public msgSuccess: Message[] = [];
    uaps: Unit[];
    ateliers: Unit[];
    currentUnit: Unit;
    ilots: Unit[];
    constructor(private uniteService: UnitManagementService, private router: Router,
        private confirmationService: ConfirmationService, private translate: TranslateService,
      ) {
     
    }
    private getUap(): void {

        this.uniteService.getByType(UniteTypeEnum.UAP).subscribe((data: Unit[]) => { this.uaps = data; console.log(data); },
          error => console.log(error),
          () => console.log('Get all Items complete'));
      }
      private getAtelierByParent(): void {
        this.uniteService.getByParent(this.currentUap).subscribe((data: Unit[]) => { this.ateliers = data; console.log(data); },
          error => console.log(error),
                () => console.log('Get all parents complete'));
      }
      private getIlotByParent(): void {
        this.uniteService.getByParent(this.currentAtelier).subscribe((data: Unit[]) => { this.ilots = data; console.log(data); },
          error => console.log(error),
                () => console.log('Get all parents complete'));
      }
     
      /**
       * function init
       */
    
      public saveUnit() {
        this.isSaving = false;
        this.msgs = [];
        this.msgSuccess = [];
    console.log(this.currentUnit);
         
          this.uniteService.add(this.currentUnit).subscribe(response => {
            this.isSaving = true;
            this.msgSuccess.push({ severity: 'success', summary: this.translate.instant('message.save.successMsgTitle'), detail: this.translate.instant("message.save.successMsg") });
          
            //go to the next Operator
          this.currentUnit = new Unit("",null ,null);
          },
            error => {
              var bodyMsg = JSON.parse(error._body);
              if (error._body && bodyMsg) {
                this.msgs.push({ severity: 'error', summary: '', detail: bodyMsg.errors[0] });
              } else {
                this.msgs.push({ severity: 'error', summary: 'Error Message', detail: 'Validation failed' });
              }
            });
            
    
        //}
      }

    ngOnInit() {

        this.data1 = [{
            label: 'Zodiac',
            type: 'person',
            styleClass: 'ui-person',
            expanded: true,
            data: { name: 'zodiac aerospace', 'avatar': 'walter.jpg' },
            children: [
                {
                    label: 'UAP1',
                    type: 'person',
                    styleClass: 'ui-person',
                    expanded: true,
                    data: { name: 'Saul Goodman', 'avatar': 'saul.jpg' },
                    children: [{
                        label: 'Atelier1',

                        styleClass: 'department-cfo'
                    },
                    {
                        label: 'Atelier2',

                        styleClass: 'department-cfo'

                    }],
                },
                {
                    label: 'UAP2',
                    type: 'person',
                    styleClass: 'ui-person',
                    expanded: true,
                    data: { name: 'Mike E.', 'avatar': 'mike.jpg' },
                    children: [{
                        label: 'Atelier3',

                        styleClass: 'department-coo'
                    }]
                },
                {
                    label: 'UAP3',
                    type: 'person',
                    styleClass: 'ui-person',
                    expanded: true,
                    data: { name: 'Jesse Pinkman', 'avatar': 'jesse.jpg' },
                    children: [{
                        label: 'Atelier4',
                        type: 'atelier',
                        styleClass: 'department-cto',
                        expanded: true,
                        children: [{
                            label: 'Ilot1',
                            type: 'ilot',
                            styleClass: 'department-cto'
                        },
                        {
                            label: 'Ilot2',

                            styleClass: 'department-cto'
                        },
                        {
                            label: 'Ilot3',

                            styleClass: 'department-cto'
                        }]
                    },
                    {
                        label: 'Atelier5',

                        styleClass: 'department-cto'
                    },
                    {
                        label: 'Atelier6',

                        styleClass: 'department-cto'
                    }]
                }
            ]
        }];

        this.items = [
            {
                label: 'Nouveau',
                icon: 'fa-plus',
                command: (event) => {
                    //event.originalEvent: Browser event
                    //event.item: menuitem metadata
                    console.log('Nouveau click');
                    this.display = true;
                    this.isEditMode = false;
                }
            }
          
        ];

    }
    onEdit(event) {

        console.log(event);
    }



    onNodeSelect(event) {
        console.log(event);

        this.currentNode = event.node;
        this.selectedNode = event.node;
        //this.displayMenu = true;
        //console.log(event.node.type);
        this.setMenuItems(event.node);
      



        this.messages = [{ severity: 'success', summary: 'Node Selected', detail: event.node.label }];

    }
    showDialog() {
        this.display = true;

    }

    onNodeUnSelect(event) {
        this.currentNode = event.node;
       // this.displayMenu = true;
        //this.selectedNode = event.node;
        //console.log(event.node.type);
        //this.setMenuItems(event.node);
        //this.messages = [{ severity: 'success', summary: 'Node Selected', detail: event.node.label }];

    }


    private setMenuItems(currentNode: TreeNode) {
        var strLabelNew: string;
        var strLabelEdit: string;



    }
}
