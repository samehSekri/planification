import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { AuthenticationService } from '../authentication.service';

@Injectable()
export class RoleGuard implements CanActivate {

    constructor(
      private auth: AuthenticationService,
      private router: Router
    ) {}

    canActivate(route: ActivatedRouteSnapshot,
                state: RouterStateSnapshot): boolean {

      const userRole: string = this.auth.getRoleName().toString();
      const routeRoles: string[] = route.data['roles'];
      const roles: string = routeRoles.join();
      if(roles.includes(userRole)){
        return true;
      }
      // for (const r in userRoles) {
      //   if (roles.includes(userRoles[r]['authority'])) {
      //     return true;
      //   }
      // }

      //this.router.navigate(['unauthorized']);
      return false;
    }
}
