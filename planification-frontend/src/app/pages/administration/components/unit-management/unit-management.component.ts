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
import { arrayToTree } from 'performant-array-to-tree'




@Component({
    selector: 'unit-management',
    providers: [UnitManagementService],
    templateUrl: './unit-management.component.html',
    styleUrls: ['./unit-management.component.scss'],

    encapsulation: ViewEncapsulation.None
})

export class UnitManagementComponent implements OnInit {

    public currentUap: Unit;
    public displayMenu: boolean = false;
    public data1: TreeNode[];
    public display: boolean = false;
    public isEditMode = false;
    public selectedNode: TreeNode;
    public currentNode: TreeNode;
    public messages: Message[];
    public currentAtelier: Unit;
    public isSaving: boolean;
    public msgs: Message[] = [];
    public msgSuccess: Message[] = [];
    public uaps: Unit[];
    public units: TreeNode[];
    public ateliers: Unit[];
    public currentUnit: Unit;
    public ilots: Unit[];
    public children: TreeNode[];

    public currentAtelierName: string;
    public currentUapName: string;

    public item: Unit;
    public displayHoraire: boolean = false;
    items: any;
    constructor(private uniteService: UnitManagementService, private router: Router,
        private confirmationService: ConfirmationService, private translate: TranslateService,
    ) {

    }
    public getUap(): void {

        this.uniteService.getByType(UniteTypeEnum.UAP).subscribe((data: Unit[]) => {
            this.uaps = data;
            if (this.uaps.length > 0) {
                this.currentUap = data[0];
                this.getAtelierByParent(this.currentUap);
            }
            console.log(data);
        },
            error => console.log(error),
            () => console.log('Get all Items complete'));
    }

    public getAtelierByParent(uap: Unit): void {
        this.currentUap = uap;

        this.uniteService.getByParent(this.currentUap).subscribe((data: Unit[]) => {
            this.ateliers = data;
            console.log(data);
        },
            error => console.log(error),
            () => console.log('Get all parents complete'));
    }

    public getIlotByParent(): void {
        this.uniteService.getByParent(this.currentAtelier).subscribe((data: Unit[]) => { this.ilots = data; console.log(data); },
            error => console.log(error),
            () => console.log('Get all parents complete'));
    }


    public saveUnit() {
        this.isSaving = false;
        this.msgs = [];
        this.msgSuccess = [];
        let type = this.currentUnit.type.toString();
        if (this.currentUnit) {
            if (this.currentUnit.type === UniteTypeEnum.ATELIER) {

                let atelier = new Unit(this.currentAtelierName, UniteTypeEnum.ATELIER, this.currentUap);
                this.currentUnit.type = UniteTypeEnum.ILOT;

                this.currentUnit.parent = atelier;


            } else if (this.currentUnit.type === UniteTypeEnum.ILOT) {
                this.currentUnit.parent = this.currentAtelier;
            } else {
                let uap = new Unit(this.currentUapName, UniteTypeEnum.UAP, null);
                let atelier = new Unit(this.currentAtelierName, UniteTypeEnum.ATELIER, uap);
                this.currentUnit.type = UniteTypeEnum.ILOT;
                this.currentUnit.parent = atelier;
                // this.currentUnit = this.currentUap;


            }
        }

        this.uniteService.add(this.currentUnit, type).subscribe(response => {
            this.isSaving = true;
            this.msgSuccess.push({ severity: 'success', summary: this.translate.instant('message.save.successMsgTitle'), detail: this.translate.instant("message.save.successMsg") });

        },
            error => {
                var bodyMsg = JSON.parse(error._body);
                if (error._body && bodyMsg) {
                    this.msgs.push({ severity: 'error', summary: '', detail: bodyMsg.errors[0] });
                } else {
                    this.msgs.push({ severity: 'error', summary: 'Error Message', detail: 'Validation failed' });
                }
            });

        this.getUap();
        //this.getAtelierByParent(this.currentUap);



        //}

    }

    ngOnInit() {

        this.currentUnit = new Unit("", null, null);
        this.currentUap = new Unit("", null, null);
        this.ateliers = [];
        this.uaps = [];

      this.uniteService.getAll().subscribe(data => {
       let response = data;
     console.log(data);
    });

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


    }

    onEdit(event) {

        console.log(event);
    }



    onNodeSelect(event) {
        console.log(event);

        this.currentNode = event.node;
        this.selectedNode = event.node;
        this.setMenuItems(event.node);
        this.showDialog();



        this.messages = [{ severity: 'success', summary: 'Node Selected', detail: event.node.label }];

    }
    showDialog() {
        this.display = true;
        this.getUap();



    }
    showDialogHorraire() {
        this.displayHoraire = true;



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

    public changeAtelier(atelier: Unit) {
        console.log(atelier);

        this.currentAtelier = atelier;
    }
}
