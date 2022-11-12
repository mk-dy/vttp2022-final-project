import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';


@Component({
  selector: 'app-creation-status',
  templateUrl: './creation-status.component.html',
  styleUrls: ['./creation-status.component.css']
})
export class CreationStatusComponent implements OnInit {

  creationMsg!: string
  sub$!: Subscription

  constructor() { }

  ngOnInit(): void {
    // this.sub$ = this.userSvc.onCreateAcc.subscribe(data => {
    //   this.creationMsg = data
    // })
  }

}
