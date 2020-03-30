package com.example.dom2;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.VolumeShaper;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {
EditText input;
Button showBtn;
    private Button button;
    private TextView tvDisplayChoice;
    ListView listView;
    String mTitle[] = {"Leb", "Mleko", "Sirenje"};
    int test=0;
    int te=0;
    int tes=0;
    int images[] = {R.drawable.bread, R.drawable.milk, R.drawable.cheese};
    // so our images and other things are set in array
    // now paste some images in drawable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Dodadi proizvod");
        builder.setMessage("Napisete go proizvodot sto sakate da go dodadete");
        input=new EditText(this);
        builder.setView(input);
        builder.setPositiveButton("Da", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String txt=input.getText().toString();
                        Toast.makeText(getApplicationContext(),txt,Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("Ne", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
dialog.dismiss();
                    }
                });
                final AlertDialog ad=builder.create();
                listView = findViewById(R.id.listView);
        int currentOrientation = getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.blank_fragment);
        }
        else {
            setContentView(R.layout.activity_main);
        }
        button= (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }

        });
        button=(Button) findViewById(R.id.buttonact);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ad.show();
                }
            });
        listView = findViewById(R.id.listView);

        // now create an adapter class

        MyAdapter adapter = new MyAdapter(this, mTitle,images);
        listView.setAdapter(adapter);
        // there is my mistake...
        // now again check this..

        // now set item click on list view
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position ==  0) {
                    test=test+1;
                    Toast.makeText(MainActivity.this, "leb kliknato "+test, Toast.LENGTH_SHORT).show();
                }
                if (position ==  1) {
                    te=te+1;
                    Toast.makeText(MainActivity.this, "mleko kliknato "+te,Toast.LENGTH_SHORT).show();
                }
                if (position ==  2) {
                    tes=tes+1;
                    Toast.makeText(MainActivity.this, "sirenje kliknato "+tes, Toast.LENGTH_SHORT).show();
                }
            }
        });
        // so item click is done now check list view
    }

    public void openDialog(){
        PrimerDialog primerDialog=new PrimerDialog();
        primerDialog.show(getSupportFragmentManager(),"primer dialog");
    }
    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        String rTitle[];
        int rImgs[];

        MyAdapter (Context c, String title[],int imgs[]) {
            super(c, R.layout.row, R.id.textView1, title);
            this.context = c;
            this.rTitle = title;
            this.rImgs = imgs;

        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            ImageView images = row.findViewById(R.id.image);
            TextView myTitle = row.findViewById(R.id.textView1);

            // now set our resources on views
            images.setImageResource(rImgs[position]);
            myTitle.setText(rTitle[position]);

            return row;
        }
    }
}
