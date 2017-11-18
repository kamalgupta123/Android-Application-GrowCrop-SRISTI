package com.farm.sristi.ssis17;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static android.R.attr.inset;
import static android.os.Build.VERSION_CODES.M;


public class PostActivityFlowering extends AppCompatActivity {
    private ImageButton  mSelectImage;

    private EditText mPostTitle;
    private EditText mPostDesc;
   private EditText mPostOther;
    private EditText mPostPerentage;


    private Button mSubmitBtn;


    private RadioGroup InsectPresenceRadioGroup;
    private RadioButton insectPresenceRadioButton;

    private RadioGroup InsectIntenstityRadioGroup;//intensity radio group
    private RadioButton instectIntenstiyRadioButton;//intenstiy scale radio button


    private RadioGroup DiseasePresenceRadioGroup;
    private RadioButton DiseasePresenceRadioButton;



    private RadioGroup DiseaseIntensityRadioGroup;
    private RadioButton DiseaseIntensityRadioButton;

    private RadioGroup DroughtRadioGroup;
    private RadioButton DroughtRadioButton;

    private RadioGroup WeedRadioGroup;
    private RadioButton WeedRadioButton;

    private RadioGroup NutrientRadioGroup;
    private RadioButton NutrientRadioButton;


    private RadioGroup LodgingRadioGroup;
    private RadioButton LodgingRadioButton;


    public Uri mImageUri = null;
    private static final int GALLERY_REQUEST = 1;
    private StorageReference mStorage;
    private DatabaseReference mDatabase;
    private ProgressDialog mProgress;
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    private DatabaseReference mDatabaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_flowering);



        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        mStorage = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Flowering_stage");
        mDatabaseUser = FirebaseDatabase.getInstance().getReference().child("Users").child(mCurrentUser.getUid());
        mSelectImage = (ImageButton) findViewById(R.id.imageSelect);

        mPostTitle = (EditText) findViewById(R.id.titleField);
        mPostDesc = (EditText) findViewById(R.id.descField);
        mPostOther=(EditText)findViewById(R.id.otherField);
        mPostPerentage=(EditText)findViewById(R.id.percentgeField);

        mSubmitBtn = (Button) findViewById(R.id.submitBtn);

        mProgress = new ProgressDialog(this);
        mSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST);
            }
        });
        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPosting();
            }
        });
    }
    private void startPosting() {
        mProgress.setMessage("Sending Details to National Innvoation Foundation");
        final String title_val = mPostTitle.getText().toString().trim();
        final String desc_val = mPostDesc.getText().toString().trim();
        final String other_val=mPostOther.getText().toString().trim();
        final String percentage_val=mPostPerentage.getText().toString().trim();

        InsectPresenceRadioGroup=(RadioGroup)findViewById(R.id.insectPresenceRadioGroup);
        int selectedId = InsectPresenceRadioGroup.getCheckedRadioButtonId();
        insectPresenceRadioButton=(RadioButton)findViewById(selectedId);
        final String insectPresnece_val=insectPresenceRadioButton.getText().toString().trim();



        InsectIntenstityRadioGroup=(RadioGroup)findViewById(R.id.insectIntensityRadioGroup);
        int selectedIdInsectIntensity=InsectIntenstityRadioGroup.getCheckedRadioButtonId();
        instectIntenstiyRadioButton=(RadioButton)findViewById(selectedIdInsectIntensity);
        final String insectIntensity_val=instectIntenstiyRadioButton.getText().toString().trim();



        DiseasePresenceRadioGroup=(RadioGroup)findViewById(R.id.DiseasePreseneceRadioGroup);
        int selectedIdDiseasePresence=DiseasePresenceRadioGroup.getCheckedRadioButtonId();
        DiseasePresenceRadioButton=(RadioButton)findViewById(selectedIdDiseasePresence);
        final String diseasePresence_val=DiseasePresenceRadioButton.getText().toString().trim();



        DiseaseIntensityRadioGroup=(RadioGroup)findViewById(R.id.IntensityOfDiseaseRadioGroup);
        int selectedIdDiseaseIntensity=DiseaseIntensityRadioGroup.getCheckedRadioButtonId();
        DiseaseIntensityRadioButton=(RadioButton)findViewById(selectedIdDiseaseIntensity);
        final String diseaseIntensity_val=DiseaseIntensityRadioButton.getText().toString().trim();


        DroughtRadioGroup=(RadioGroup)findViewById(R.id.DroughtRadioGroup);
        int selectedIdDrought=DroughtRadioGroup.getCheckedRadioButtonId();
        DroughtRadioButton=(RadioButton)findViewById(selectedIdDrought);
        final String drought_val=DroughtRadioButton.getText().toString().trim();

        WeedRadioGroup=(RadioGroup)findViewById(R.id.WeedRadioGroup);
        int selecetedIdWeed=WeedRadioGroup.getCheckedRadioButtonId();
        WeedRadioButton=(RadioButton)findViewById(selecetedIdWeed);
        final String weed_val=WeedRadioButton.getText().toString().trim();


        NutrientRadioGroup=(RadioGroup)findViewById(R.id.NutrientsRadioGroup);
        int selectedIdNutrinent=NutrientRadioGroup.getCheckedRadioButtonId();
        NutrientRadioButton=(RadioButton)findViewById(selectedIdNutrinent);
        final String nutrient_val=NutrientRadioButton.getText().toString().trim();


        LodgingRadioGroup=(RadioGroup)findViewById(R.id.LodgingRadioGroup);
        int selectedIdLodging=LodgingRadioGroup.getCheckedRadioButtonId();
        LodgingRadioButton=(RadioButton)findViewById(selectedIdLodging);
        final String lodging_val=LodgingRadioButton.getText().toString().trim();



        if(!TextUtils.isEmpty(title_val) && !TextUtils.isEmpty(desc_val)&&mImageUri != null){
            mProgress.show();
            StorageReference filepath = mStorage.child("Blog_Images").child(mImageUri.getLastPathSegment());
            filepath.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    final Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    final DatabaseReference newPost = mDatabase.push();
                    mDatabaseUser.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            newPost.child("title").setValue(title_val);
                            newPost.child("desc").setValue(desc_val);
                            newPost.child("other_comments").setValue(other_val);
                            newPost.child("Percentage Flowering").setValue(percentage_val);
                            newPost.child("Insect_Presnece").setValue(insectPresnece_val);
                            newPost.child("Insect_Intensity").setValue(insectIntensity_val);
                            newPost.child("Disease_Presenece").setValue(diseasePresence_val);
                            newPost.child("Disease_intensty").setValue(diseaseIntensity_val);
                            newPost.child("Drought_condition").setValue(drought_val);
                            newPost.child("Weed_condition").setValue(weed_val);
                            newPost.child("Nutrinent_condition").setValue(nutrient_val);
                            newPost.child("Lodging_condition").setValue(lodging_val);

                            newPost.child("image").setValue(downloadUrl.toString());
                            newPost.child("uid").setValue(mCurrentUser.getUid());
                            newPost.child("username").setValue(dataSnapshot.child("name").getValue()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()) {
                                        startActivity(new Intent(PostActivityFlowering.this, VarietyMainPage.class));
                                    }
                                }
                            });
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
                    mProgress.dismiss();
                }
            });
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERY_REQUEST && resultCode == RESULT_OK){
            mImageUri = data.getData();
            mSelectImage.setImageURI(mImageUri);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if(item.getItemId() == R.id.action_logout){
            logout();
        }
        return super.onOptionsItemSelected(item);
    }
    private void logout() {
        mAuth.signOut();
    }
}
