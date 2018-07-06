package com.example.anton.ma_ced;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class CalendarFragment extends Fragment {

    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM - yyyy", Locale.getDefault());
    private CompactCalendarView compactCalendarView;
    private TextView textViewCalendarDate;

    public CalendarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(
                savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final List<String> mutableBookings = new ArrayList<>();

        View fragmentCalendarView = inflater.inflate(R.layout.fragment_calendar, container, false);
        compactCalendarView = fragmentCalendarView.findViewById(R.id.compactcalendar_view);

        // below allows you to configure color for the current day in the month
        // compactCalendarView.setCurrentDayBackgroundColor(getResources().getColor(R.color.black));
        // below allows you to configure colors for the current day the user has selected
        // compactCalendarView.setCurrentSelectedDayBackgroundColor(getResources().getColor(R.color.dark_red));
        compactCalendarView.setUseThreeLetterAbbreviation(false);
        compactCalendarView.setFirstDayOfWeek(java.util.Calendar.MONDAY);
        compactCalendarView.setIsRtl(false);
        //compactCalendarView.setIsRtl(true);

        //clear compactCalenderView
        compactCalendarView.removeAllEvents();
        //load events
        compactCalendarView.addEvents(Patient.instance().getEventList());

        compactCalendarView.invalidate();

        // below line will display Sunday as the first day of the week
        // compactCalendarView.setShouldShowMondayAsFirstDay(false);

        // disable scrolling calendar
        // compactCalendarView.shouldScrollMonth(false);

        // show days from other months as greyed out days
        compactCalendarView.displayOtherMonthDays(true);

        // show Sunday as first day of month
        // compactCalendarView.setShouldShowMondayAsFirstDay(false);

        //set initial title
        textViewCalendarDate = fragmentCalendarView.findViewById(R.id.textView_calendar_date);
        textViewCalendarDate.setText(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));

        //set title on calendar scroll
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                textViewCalendarDate.setText(dateFormatForMonth.format(dateClicked));

                //todo
                Intent intent = new Intent(getContext(), StoolList.class);
                java.util.Calendar calendar = java.util.Calendar.getInstance();
                calendar.setTime(dateClicked);  //converts date to calendar
                //intent.putExtra("calendar", calendar);   //pass date to StoolList.class
                Patient.instance().setCurrentCalendar(calendar);//alternate to putExtra
                startActivity(intent);
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                textViewCalendarDate.setText(dateFormatForMonth.format(firstDayOfNewMonth));
            }
        });

        compactCalendarView.setAnimationListener(new CompactCalendarView.CompactCalendarAnimationListener() {
            @Override
            public void onOpened() {
            }

            @Override
            public void onClosed() {
            }
        });


        // uncomment below to show indicators above small indicator events
        // compactCalendarView.shouldDrawIndicatorsBelowSelectedDays(true);

        // uncomment below to open onCreate
        //openCalendarOnCreate(v);

        return fragmentCalendarView;
    }

    @Override
    public void onResume() {
        super.onResume();
        textViewCalendarDate.setText(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));
        // Set to current day on resume to set calendar to latest day
        // toolbar.setTitle(dateFormatForMonth.format(new Date()));

        //clear compactCalenderView
        compactCalendarView.removeAllEvents();
        //load events
        compactCalendarView.addEvents(Patient.instance().getEventList());
    }
}
