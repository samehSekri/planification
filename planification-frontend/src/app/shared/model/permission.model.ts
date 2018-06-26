import { PermissionEnum } from "app/shared/model/enumeration/permission.enum";

export class Permission {
    /**
     * Permission model
     * @param id : id permission
     * @type : number
     * @param name : the permission name
     * @type PermissionEnum
     */
    constructor(public id: number, name: PermissionEnum) {
    }
}