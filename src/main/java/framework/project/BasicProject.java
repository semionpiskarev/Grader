package framework.project;

import framework.execution.*;
import grader.project.AProject;
import grader.project.folder.ARootCodeFolder;
import grader.sakai.project.SakaiProject;
import grader.trace.project.BinaryFolderMade;
import grader.trace.project.BinaryFolderNotFound;
import grader.trace.project.ProjectFolderNotFound;
import grader.trace.project.SourceFolderAssumed;
import grader.trace.project.SourceFolderNotFound;
import grader.util.IntrospectionUtil;
import scala.Option;
import tools.DirectoryUtils;
import util.pipe.InputGenerator;
import util.trace.TraceableLog;
import util.trace.TraceableLogFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * A "standard" project. That is, an IDE-based java project.
 */
public class BasicProject implements Project {
    

    private File directory;
    private File sourceFolder;
    private Option<ClassesManager> classesManager;
    private TraceableLog traceableLog;
    boolean noSrc;
//    protected SakaiProject project;

    /**
     * Basic constructor
     *
     * @param aDirectory The location of the project
     * @param name      The name of the project, such as "Assignment1"
     * @throws FileNotFoundException
     */
//    public StandardProject(File directory, String name) throws FileNotFoundException {
//        // Find the folder. We could be there or it could be in a different folder
//    	if (directory == null) return;
//        Option<File> src = DirectoryUtils.locateFolder(directory, "src");
//        if (src.isEmpty()) {
//          throw new FileNotFoundException("No src folder");
////
////        	noSrc = true;
////        	sourceFolder = directory;
////        	this.directory = sourceFolder;
//        } else {
//        sourceFolder = src.get();
//        this.directory = src.get().getParentFile();
//        }
//
//        try {
//            File sourceFolder = new File(this.directory, "src");
//            File buildFolder = getBuildFolder("main." + name);
//            classesManager = Option.apply((ClassesManager) new ProjectClassesManager(buildFolder, sourceFolder));
//        } catch (Exception e) {
//            classesManager = Option.empty();
//        }
//
//        // Create the traceable log
//        traceableLog = TraceableLogFactory.getTraceableLog();
//
//    }
//	public StandardProject(SakaiProject project, File aDirectory, String name) throws FileNotFoundException {
//		
//	}
    // rewriting Josh's code
    // going back to Josh';s code
    public BasicProject(/*SakaiProject aProject,*/ File aDirectory, String name) throws FileNotFoundException {
        // Find the folder. We could be there or it could be in a different folder
    	if (aDirectory == null) {
            throw new FileNotFoundException("No directory given");
        }
    	// will do this in standardproject
//    	project = aProject;
    	directory = aDirectory;
//        Option<File> src = DirectoryUtils.locateFolder(aDirectory, "src");
        Option<File> src = DirectoryUtils.locateFolder(aDirectory, Project.SOURCE);

        if (src.isEmpty()) {
        	System.out.println(SourceFolderNotFound.newCase(aDirectory.getAbsolutePath(), this).getMessage());

        	Set<File> sourceFiles = DirectoryUtils.getSourceFiles(aDirectory);
        	if (!sourceFiles.isEmpty()) {
                    File aSourceFile = sourceFiles.iterator().next();
                    sourceFolder = aSourceFile.getParentFile(); // assuming no packages!
                    this.directory = sourceFolder.getParentFile();
                    SourceFolderAssumed.newCase(sourceFolder.getAbsolutePath(), this);
        	} else {
                    System.out.println(ProjectFolderNotFound.newCase(aDirectory.getAbsolutePath(), this).getMessage());
                    throw new FileNotFoundException("No source files found");
        	}
        	noSrc = true;
//                throw new FileNotFoundException("No src folder");
//        	sourceFolder = aDirectory;
//        	this.directory = sourceFolder;
        } else {
            sourceFolder = src.get();
            this.directory = src.get().getParentFile();
        }
        

        try {
//            File sourceFolder = new File(this.directory, "src");
            File buildFolder = getBuildFolder("main." + name);
//            if (AProject.isMakeClassDescriptions())
//            classesManager = Option.apply((ClassesManager) new ProjectClassesManager(project, buildFolder, sourceFolder));
            classesManager = createClassesManager(buildFolder);

        
        } catch (Exception e) {
        	e.printStackTrace();
            classesManager = Option.empty();
        }

        // Create the traceable log
        traceableLog = TraceableLogFactory.getTraceableLog();
    }
    protected Option<ClassesManager> createClassesManager(File buildFolder) throws ClassNotFoundException, IOException {
//        classesManager = Option.apply((ClassesManager) new ProjectClassesManager(project, buildFolder, sourceFolder));

       return Option.apply((ClassesManager) new BasicProjectClassesManager(null, buildFolder, sourceFolder));

    }

    /**
     * This figures out where the build folder is, taking into account variations due to IDE
     *
     * @param preferredClass The name of the class that has the main method, such as "main.Assignment1"
     * @return The build folder
     * @throws FileNotFoundException
     */
    public File getBuildFolder(String preferredClass) throws FileNotFoundException {
//        Option<File> out = DirectoryUtils.locateFolder(directory, "out");
        Option<File> out = DirectoryUtils.locateFolder(directory, Project.BINARY_2);
//        if (out.isEmpty())
//        	out = DirectoryUtils.locateFolder(directory, Project.BINARY_0);

        

//        Option<File> bin = DirectoryUtils.locateFolder(directory, "bin");
        Option<File> bin = DirectoryUtils.locateFolder(directory,  Project.BINARY_0); // just to handle grader itself, as it has execuot.c
        if (bin.isEmpty())
//        Option<File> bin = DirectoryUtils.locateFolder(directory,  Project.BINARY);
        	bin = DirectoryUtils.locateFolder(directory,  Project.BINARY);




        // If there is no 'out' or 'bin' folder then give up
        if (out.isEmpty() && bin.isEmpty()) {
        	if (noSrc) {
                    return sourceFolder;
                } 
//            throw new FileNotFoundException();
        	BinaryFolderNotFound.newCase(directory.getAbsolutePath(), this);
        	File retVal = new File(directory, Project.BINARY);
        	retVal.mkdirs();
//        	project.getClassLoader().setBinaryFileSystemFolderName(retVal.getAbsolutePath());
        	BinaryFolderMade.newCase(retVal.getAbsolutePath(), this);
        	return retVal.getAbsoluteFile();
        	
        } else {
            // There can be more folders under it, so look around some more
            // But first check the class name to see what we are looking for
            File dir = null;
            if (out.isDefined()) {
                dir = out.get();
            }
            if (bin.isDefined()) {
                dir = bin.get();
            }
            if (preferredClass == null || preferredClass.isEmpty()) {
                return dir;
            }

            if (preferredClass.contains(".")) {
                Option<File> packageDir = DirectoryUtils.locateFolder(dir, preferredClass.split("\\.")[0]);
                if (packageDir.isDefined()) {
                    return packageDir.get().getParentFile();
                } else {
                    return dir;
                }
            } else {
                return dir;
            }
        }
    }

    @Override
    public TraceableLog getTraceableLog() {
        return traceableLog;
    }

    @Override
    public RunningProject start(String input) throws NotRunnableException {
        return new ReflectionRunner(this).run(input);
    }

    @Override
    public RunningProject launch(String input) throws NotRunnableException {
        return new ProcessRunner(this).run(input);
    }

    @Override
    public RunningProject start(String input, int timeout) throws NotRunnableException {
        return new ReflectionRunner(this).run(input, timeout);
    }

    @Override
    public RunningProject launch(InputGenerator anOutputBasedInputGenerator, String input, int timeout) throws NotRunnableException {
        return new ProcessRunner(this).run(anOutputBasedInputGenerator, input, timeout);
    }
    @Override
    public RunningProject launch(InputGenerator anOutputBasedInputGenerator, Map<String, String> aProcessToInput, int timeout) throws NotRunnableException {
        return new ProcessRunner(this).run(anOutputBasedInputGenerator, aProcessToInput, timeout);
    }
    
    @Override
    public RunningProject launch( String input, int timeout) throws NotRunnableException {
        return new ProcessRunner(this).run(input, timeout);
    }
    @Override
    public RunningProject launch( String input, String[] anArgs, int timeout) throws NotRunnableException {
        return new ProcessRunner(this).run(input, anArgs, timeout);
    }

    @Override
    public RunningProject launchInteractive() throws NotRunnableException {
    	RunningProject retVal = new InteractiveConsoleProcessRunner(this).run("");
//    	retVal.createFeatureTranscript();
    	return retVal;
//        return new InteractiveConsoleProcessRunner(this).run("");
    }
    @Override
    public RunningProject launchInteractive(String[] args) throws NotRunnableException {
    	RunningProject retVal = new InteractiveConsoleProcessRunner(this).run("", args);
//    	retVal.createFeatureTranscript();
    	return retVal;
//        return new InteractiveConsoleProcessRunner(this).run("");
    }

    @Override
    public Option<ClassesManager> getClassesManager() {
        return classesManager;
    }

    @Override
    public File getSourceFolder() {
        return sourceFolder;
    }
    public static void main (String[] args) {
    	try {
			AProject.setLoadClasses(true);
//			Project aProject = new BasicProject(null, new File("."), null);
			Project aProject = new BasicProject(new File("."), null);

			Class aClass = IntrospectionUtil.findClass(aProject, "ACartesianPoint");
			System.out.println (aClass);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}