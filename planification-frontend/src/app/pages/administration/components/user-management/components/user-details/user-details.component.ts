import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { UserManagementService } from "app/pages/administration/components/user-management/user-management.service";
import { RoleNameEnum } from "app/shared/model/enumeration";
import { User } from "app/shared/model";

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html'
})
export class UserDetailsComponent implements OnInit {
  public user: User;
  public formTitle: string;

  constructor(private route: ActivatedRoute, private router: Router,
    private userManagementService: UserManagementService) {
  }

/**
 * initialize component
 */
  ngOnInit() {
    this.route.params.subscribe(params => this.loadUser(params['id']));
  }

  /**
   * find a user by id
   * @param id 
   */
  private loadUser(id: any) {
    if (id === 'new') {
      this.formTitle = "general.userDetails.createTitle";
      this.user = new User(null, "", null, null, null, null, [], true, new Date(), RoleNameEnum.ROLE_EMPTY, []);
    } else if(Number(id)) {
      this.formTitle = "general.userDetails.editTitle";
      this.userManagementService.getById(id).subscribe(user => {
        this.user = user;
      });
    }
  }
}
