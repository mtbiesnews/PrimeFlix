package com.rcloud.business.fragment;

import static android.app.Activity.RESULT_OK;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;

import androidx.activity.result.ActivityResult;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.rcloud.netflix.R;

import java.io.FileInputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

import android.util.Log;
import android.widget.Toast;


public class CareerFragment extends Fragment {

    private final int PICK_DOCUMENT = 1001;
    private static final String TAG = "MainActivity";
    String[] relation = { "Select Relation", "Single", "In a relationship ", "Engaged ", "Married ", "Divorced  ", "Widowed "};
    String[] gender = { "Select gender", "Male", "Female", "Custom "};
    String[] intest = { "Select Interest", "Men", "Women", "both "};
    String[] emptype = { "Select Employment", "Full time", "Part time", "Self employed ","FREELANCE", "CANTRACT", "INTERNSHIP", "VOLUNTTER"};
    Spinner spinner_res,spinner_gender,spinner_interest;
    RelativeLayout opencloase,rel_work,eduction,rel_skill,rel_featered,opencloasecontact;
    LinearLayout viewyes,work,educ,ll_contact;
    ImageView move,movecontact,edit_contact;
    String status ="0";
    String pro_edu ="1";
    String contactstatus ="0";
    Calendar myCalendarstart,myCalendarend;
    EditText enddate,statedate;
    TextView deptAddedu, show_path;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_career, container, false);
//        opencloase = view.findViewById(R.id.opencloase);
//        opencloasecontact = view.findViewById(R.id.opencloasecontact);
        rel_work = view.findViewById(R.id.rel_work);
        rel_skill = view.findViewById(R.id.rel_skill);
        rel_featered = view.findViewById(R.id.rel_featered);
        educ = view.findViewById(R.id.educ);
        ll_contact = view.findViewById(R.id.ll_contact);
        eduction = view.findViewById(R.id.eduction);
        viewyes = view.findViewById(R.id.viewyes);
        edit_contact = view.findViewById(R.id.edit_contact);
//        work = view.findViewById(R.id.work);
//        move = view.findViewById(R.id.move);
      //  movecontact = view.findViewById(R.id.movecontact);
        deptAddedu = view.findViewById(R.id.deptAddedu);
        spinner_res = view.findViewById(R.id.spinner_res);
        spinner_gender = view.findViewById(R.id.spinner_gender);
        spinner_interest = view.findViewById(R.id.spinner_interest);
        viewyes.setVisibility(View.VISIBLE);


//        opencloase.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (status.equals("0")){
//                    status = "1";
//                    viewyes.setVisibility(View.GONE);
//                    //    work.setVisibility(View.GONE);
//                    move.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_baseline_keyboard_arrow_down_24));
//                }else{
//                    status = "0";
//                    viewyes.setVisibility(View.VISIBLE);
//                    // work.setVisibility(View.GONE);
//                    move.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_baseline_keyboard_arrow_up_24));
//
//                }
//            }
//        });
//
//        opencloasecontact.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (contactstatus.equals("0")){
//                    contactstatus = "1";
//                    ll_contact.setVisibility(View.GONE);
//                    //    work.setVisibility(View.GONE);
//                    movecontact.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_baseline_keyboard_arrow_down_24));
//                }else{
//                    contactstatus = "0";
//                    ll_contact.setVisibility(View.VISIBLE);
//                    // work.setVisibility(View.GONE);
//                    movecontact.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_baseline_keyboard_arrow_up_24));
//
//                }
//            }
//        });


        ArrayAdapter aa = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,relation);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_res.setAdapter(aa);
        spinner_res.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(getApplicationContext(),relation[position] , Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter aa1 = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,gender);
        aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_gender.setAdapter(aa1);
        spinner_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(),gender[position] , Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter aa2 = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,intest);
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_interest.setAdapter(aa2);
        spinner_interest.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(getApplicationContext(),intest[position] , Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        rel_skill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_Translucent_NoTitleBar);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.skill_layout);
                Window window = dialog.getWindow();
                WindowManager.LayoutParams wlp = window.getAttributes();

                wlp.gravity = Gravity.CENTER;
                wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
                window.setAttributes(wlp);
                dialog.getWindow().setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

                TextView tvAlert = dialog.findViewById(R.id.tvAlert);
                TextView save = dialog.findViewById(R.id.save);
                ImageView img = dialog.findViewById(R.id.back);

                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();


                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();

                    }
                });

            }
        });
        rel_featered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_Translucent_NoTitleBar);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.featured_layout);
                Window window = dialog.getWindow();
                WindowManager.LayoutParams wlp = window.getAttributes();
                wlp.gravity = Gravity.CENTER;
                wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
                window.setAttributes(wlp);
                dialog.getWindow().setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

                TextView tvAlert = dialog.findViewById(R.id.tvAlert);
                TextView save = dialog.findViewById(R.id.save);
                ImageView img = dialog.findViewById(R.id.back);
                Button add = dialog.findViewById(R.id.add);
                show_path = dialog.findViewById(R.id.SHOW_PATH);
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();

                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        chooseFile();
                    }
                });





                img.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View view) {
                        dialog.dismiss();

                    }
                });
            }

        });

        rel_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_Translucent_NoTitleBar);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.work_exp);
                Window window = dialog.getWindow();
                WindowManager.LayoutParams wlp = window.getAttributes();

                wlp.gravity = Gravity.CENTER;
                wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
                window.setAttributes(wlp);
                dialog.getWindow().setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

                TextView tvAlert = dialog.findViewById(R.id.tvAlert);
                TextView save = dialog.findViewById(R.id.save);
                ImageView img = dialog.findViewById(R.id.back);
                Spinner spinner_emptype = dialog.findViewById(R.id.spinner_emptype);
                CheckBox chkcurrent = dialog.findViewById(R.id.chkcurrent);
                enddate = dialog.findViewById(R.id.enddate);
                statedate = dialog.findViewById(R.id.statedate);
                TextView present = dialog.findViewById(R.id.present);

                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();

                enddate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        endDateDialog();
                    }
                });

                statedate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        startDateDialog();
                    }
                });


                chkcurrent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean checked = ((CheckBox) v).isChecked();
                        if (checked){
                            // Do your coding
                            enddate.setVisibility(View.GONE);
                            present.setVisibility(View.VISIBLE);
                        }
                        else{
                            // Do your coding
                            enddate.setVisibility(View.VISIBLE);
                            present.setVisibility(View.GONE);

                        }
                    }
                });

                ArrayAdapter aa4 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, emptype);
                aa4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_emptype.setAdapter(aa4);
                spinner_emptype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        // Toast.makeText(getApplicationContext(),emptype[position] , Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                    }
                });

                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();

                    }
                });

            }
        });



        eduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pro_edu.equals("0")){
                    pro_edu = "1";
                    educ.setVisibility(View.GONE);
                    // work.setVisibility(View.GONE);
                    viewyes.setVisibility(View.GONE);

                }else{
                    pro_edu = "0";
                    educ.setVisibility(View.VISIBLE);
                    // work.setVisibility(View.GONE);
                    viewyes.setVisibility(View.GONE);

                }
            }
        });
        deptAddedu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_Translucent_NoTitleBar);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.education);
                Window window = dialog.getWindow();
                WindowManager.LayoutParams wlp = window.getAttributes();

                wlp.gravity = Gravity.CENTER;
                wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
                window.setAttributes(wlp);
                dialog.getWindow().setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

                TextView tvAlert = dialog.findViewById(R.id.tvAlert);
                TextView save = dialog.findViewById(R.id.save);
                ImageView img = dialog.findViewById(R.id.back);

                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();


                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                    }
                });

                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();

                    }
                });
            }
        });

        return view;
    }



    private void endDateDialog() {
        Calendar myCalendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), R.style.DatePickerDialog, enddatePicker,
                myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();

    }

    private void startDateDialog() {
        Calendar myCalendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), R.style.DatePickerDialog, startdatePicker,
                myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    private void chooseFile () {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("application/pdf");
        startActivityForResult(intent, PICK_DOCUMENT);
    }


    public void onActivityResult ( int requestCode, int resultCode, @Nullable Intent resultData){
        CareerFragment.super.onActivityResult(requestCode, resultCode, resultData);
        if (requestCode == PICK_DOCUMENT && resultCode == RESULT_OK) {
            Uri uri = resultData.getData();
            if (uri.getScheme().toString().compareTo("content")==0) {
                ContentResolver cr = getActivity().getApplicationContext().getContentResolver();
                Cursor cursor = cr.query(uri, null, null, null, null);
                if (cursor.moveToFirst()) {
                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DISPLAY_NAME);
                    Uri filePathUri = Uri.parse(cursor.getString(column_index));
                    String file_name = filePathUri.getLastPathSegment().toString();
                    String file_path = filePathUri.getPath();
                    show_path.setText(file_name);
                }
            }
         //   if (resultData != null) {
         //       Log.d(TAG, "onActivityResult: " + resultData.getData());
         //       show_path.setText("File Path:" + resultData.getData());
         //   }
        }
    }




    private DatePickerDialog.OnDateSetListener startdatePicker = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            myCalendarstart = Calendar.getInstance();
            myCalendarstart.set(Calendar.YEAR, year);
            myCalendarstart.set(Calendar.MONTH, monthOfYear);
            myCalendarstart.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String myFormat = "yyyy-MM-dd";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            statedate.setText(""+sdf.format(myCalendarstart.getTime()));


        }
    };

    private DatePickerDialog.OnDateSetListener enddatePicker = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            myCalendarend = Calendar.getInstance();
            myCalendarend.set(Calendar.YEAR, year);
            myCalendarend.set(Calendar.MONTH, monthOfYear);
            myCalendarend.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String myFormat = "yyyy-MM-dd";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            enddate.setText(""+sdf.format(myCalendarend.getTime()));


        }
    };
}