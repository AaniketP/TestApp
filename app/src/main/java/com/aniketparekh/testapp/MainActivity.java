package com.aniketparekh.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import net.sf.javaml.classification.Classifier;
import net.sf.javaml.classification.bayes.NaiveBayesClassifier;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.DefaultDataset;
import net.sf.javaml.core.Instance;
import net.sf.javaml.filter.discretize.EqualWidthBinning;
import net.sf.javaml.classification.bayes.NaiveBayesClassifier;
import net.sf.javaml.tools.InstanceTools;
import net.sf.javaml.tools.data.FileHandler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static android.R.attr.path;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button button;
    Dataset data=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    public void myNaiveBayes() {

        Dataset data1 = new DefaultDataset();
            Instance tmpInstance = InstanceTools.randomInstance(25);
            data1.add(tmpInstance);
        Log.v("size", String.valueOf(data1.instance(5)));

        String yourFilePath = getApplicationContext().getFilesDir() + "/" + "iris.data";

        File f = new File( yourFilePath );
        Log.v(String.valueOf(f.length()), "abc");

        EqualWidthBinning eb = new EqualWidthBinning(20);
        System.out.println("Start discretisation");
        eb.build(data1);
        Dataset ddata = data1.copy();
        eb.filter(ddata);

        /*Log.v("abc","abc");
        try {
            data = FileHandler.loadDataset(f, 4, ",");
            Log.v(String.valueOf(data.size()), "size");
        } catch (IOException e) {
            e.printStackTrace();
        }



        *//* Discretize through EqualWidtBinning *//*
        EqualWidthBinning eb = new EqualWidthBinning(20);
        System.out.println("Start discretisation");
        eb.build(data);
        Dataset ddata = data.copy();
        eb.filter(ddata);

        boolean useLaplace = true;
        boolean useLogs = true;
        Classifier nbc = new NaiveBayesClassifier(useLaplace, useLogs, false);
        nbc.buildClassifier(data);

        Dataset dataForClassification = null;
        try {
            dataForClassification = FileHandler.loadDataset(f, 4, ",");
        } catch (IOException e) {
            e.printStackTrace();
        }

		*//* Counters for correct and wrong predictions. *//*
        int correct = 0, wrong = 0;

		*//* Classify all instances and check with the correct class values *//*
        for (Instance inst : dataForClassification) {
            eb.filter(inst);
            Object predictedClassValue = nbc.classify(inst);
            Object realClassValue = inst.classValue();
            if (predictedClassValue.equals(realClassValue))
                correct++;
            else {
                wrong++;

            }

        }
        System.out.println("correct " + correct);
        System.out.println("incorrect " + wrong);
*/

    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.button){
            myNaiveBayes();
        }

    }
}
