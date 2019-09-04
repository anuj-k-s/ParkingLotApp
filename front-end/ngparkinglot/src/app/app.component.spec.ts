import { HttpClientModule } from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { TestBed, async, getTestBed, fakeAsync } from '@angular/core/testing';
import { AppComponent } from './app.component';
import { Observable, observable } from 'rxjs';
import { of } from 'rxjs';

describe('AppComponent', () => {
  let httpMock: HttpTestingController;
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        AppComponent
      ],
      imports: [
        ReactiveFormsModule,
        HttpClientModule,
        HttpClientTestingModule,
      ], providers: [],
    }).compileComponents();
    httpMock = TestBed.get(HttpTestingController);
  }));

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  });

  it('get call should invoked on ngOninit', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    const req = httpMock.expectOne(`${app.getUrl}`);
    expect(req.request.method).toBe('GET');
  });

  it('should return the right data', () => {
    const mockData = [{'_id': 435345345, '_type': 'window'}];
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    const req = httpMock.expectOne(`${app.getUrl}`);
    req.flush(mockData);
    fixture.detectChanges();
    expect(app.parkings).toBe(mockData);
    console.log(app.parkings);

});

it('should return the right data', () => {
  const mockData = [{'_id': 435345345, '_type': 'window'}];
  const fixture = TestBed.createComponent(AppComponent);
  const app = fixture.debugElement.componentInstance;
  const req = httpMock.match(`${app.getUrl}`);
 req.forEach(req =>{req.flush(mockData)});

  fixture.detectChanges();
  app.parkNewVehicle();
  expect(app.success_message).toBe('a');


});
});
