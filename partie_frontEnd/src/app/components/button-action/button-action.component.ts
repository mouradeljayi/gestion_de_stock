import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-button-action',
  templateUrl: './button-action.component.html',
  styleUrls: ['./button-action.component.css']
})
export class ButtonActionComponent implements OnInit {

  @Input()
  isNouveauVisible = true
  @Input()
  isExporterVisible = true
  @Input()
  isImporterVisible = true

  @Output()
  clickEvent  = new EventEmitter();

  constructor() { }

  ngOnInit(): void {
  }

  buttonNouveauClick() {
    this.clickEvent.emit();
  }

}
