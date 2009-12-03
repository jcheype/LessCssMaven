package com.jcheype.lesscss.maven;

import com.asual.lesscss.Engine;
import com.asual.lesscss.EngineException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Goal which touches a timestamp file.
 *
 * @goal lesscss
 * @phase generate-resources
 */
public class LessCssMaven
        extends AbstractMojo {


    /**
     * The greeting to display.
     *
     * @parameter expression="${lesscss.dir.in}" default-value="src/main/webapp/css/"
     */
    private String lessDirIn;

    /**
     * The greeting to display.
     *
     * @parameter expression="${lesscss.dir.out}" default-value="src/main/webapp/css/"
     */
    private String lessDirOut;

    public void execute()
            throws MojoExecutionException {
        File dirIn = new File(lessDirIn);
        if (!dirIn.isDirectory())
            throw new MojoExecutionException("lesscss.dir.in must be set !");

        File dirOut = new File(lessDirOut);
        if (!dirOut.isDirectory())
            throw new MojoExecutionException("lesscss.dir.out must be set !");
        Engine engine = new Engine();

        for (File f : dirIn.listFiles()) {
            String filename = f.getName();
            if(filename.endsWith(".less")){
                try {
                    System.out.println("compiling " + f.getAbsolutePath());
                    String cssResult = engine.compile(f);

                    File fileOut = new File(dirOut, filename + ".css");
                    System.out.println("writing " + fileOut.getAbsolutePath());                    
                    fileOut.createNewFile();
                    FileOutputStream fos = new FileOutputStream(fileOut);
                    fos.write(cssResult.getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new MojoExecutionException("cannot compile: " + f.getAbsolutePath());
                }
            }
        }
    }
}
