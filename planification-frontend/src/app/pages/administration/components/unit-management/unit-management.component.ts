import { Component, OnInit, ViewEncapsulation, Input, ViewChild, ElementRef, Renderer } from '@angular/core';

import { OrganizationChartModule } from 'primeng/components/organizationchart/organizationchart';
import { Message, MenuItem, SelectItem } from 'primeng/components/common/api';
import { TreeNode } from 'primeng/primeng';
import * as $ from 'jquery';




@Component({
    selector: 'unit-management',
    templateUrl: './unit-management.component.html',
    styleUrls: ['./unit-management.component.scss'],

    encapsulation: ViewEncapsulation.None
})

export class UnitManagementComponent implements OnInit {

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
    constructor() {
     
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
            },
            {
                label: 'Edit',
                icon: 'fa-edit',
                command: (event) => {
                    //event.originalEvent: Browser event
                    //event.item: menuitem metadata
                    console.log('Edit click');
                    this.display = true;
                    this.isEditMode = true;
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
