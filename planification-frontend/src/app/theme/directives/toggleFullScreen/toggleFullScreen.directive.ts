import { Directive, HostListener } from '@angular/core';
import * as screenfull from 'screenfull';

@Directive({
    selector: '[toggleFullScreen]'
})
export class ToggleFullScreen {
    @HostListener('click') onClick() {
        if (screenfull.enabled) {
            screenfull.toggle();
        }
    }

    /**
     * Exit full screen mode
     */
    public exitFullScreen() {
        screenfull.exit();
    }
}