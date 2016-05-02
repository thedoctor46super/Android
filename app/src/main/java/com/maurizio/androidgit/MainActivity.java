package com.maurizio.androidgit;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class MainActivity extends AppCompatActivity {

    public static final int homeId = 1;
    public static final int mapId = 2;
    public static final int loginId = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMapActivity();
            }
        });

        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem item1 = new PrimaryDrawerItem()
                .withName(R.string.action_home)
                .withIcon(R.drawable.ic_home_24dp)
                .withIdentifier(homeId);
        DividerDrawerItem item2 = new DividerDrawerItem();
        SecondaryDrawerItem item3 = (SecondaryDrawerItem) new SecondaryDrawerItem()
                .withName(R.string.action_login)
                .withIcon(R.drawable.ic_face_24dp)
                .withIdentifier(loginId);
        SecondaryDrawerItem item4 = (SecondaryDrawerItem) new SecondaryDrawerItem()
                .withName(R.string.action_map)
                .withIcon(R.drawable.ic_map_24dp)
                .withIdentifier(mapId);

//create the drawer and remember the `Drawer` result object
        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        item1,
                        item2,
                        item3,
                        item4
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D

                        int itemId = (int)drawerItem.getIdentifier();

                        switch(itemId) {
                            case homeId:
                                openMainActivity();
                                break;
                            case loginId:
                                openLoginActivity();
                                break;
                            case mapId:
                                openMapActivity();
                                break;

                        }
                        return true;
                    }
                })
                .build();

        ActionBar mActionBar = getSupportActionBar();

        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);

        View viewActionBar = getLayoutInflater().inflate(R.layout.action_bar_title, null);

        //TextView mTitleTextView = (TextView) viewActionBar.findViewById(R.id.actionbar_textview);
        //mTitleTextView.setText("TALKER");

        ImageButton imgBtnLogin = (ImageButton) viewActionBar.findViewById(R.id.imgBtnLogin);
        imgBtnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                openLoginActivity();
            }
        });


        mActionBar.setCustomView(viewActionBar);
        mActionBar.setDisplayShowCustomEnabled(true);
        mActionBar.setDisplayHomeAsUpEnabled(false);
        mActionBar.setHomeButtonEnabled(false);
    }

    public void openMapActivity() {
        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
        startActivity(intent);
    }

    public void openMainActivity(){
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void openLoginActivity() {
        //Toast.makeText(MainActivity.this, "TODO: Show Login!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(MainActivity.this, NavigationActivity.class);
        startActivity(intent);
    }


/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_login) {
            Toast.makeText(MainActivity.this, "Login!!", Toast.LENGTH_LONG).show();
            return true;
        }
        if (id == R.id.action_menu) {
            Toast.makeText(MainActivity.this, "Menu!!", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */
}
