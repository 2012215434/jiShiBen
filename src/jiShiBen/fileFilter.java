package jiShiBen;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class fileFilter extends FileFilter {
	String extentionedName ;
	
	public fileFilter(String extentionedName){
		this.extentionedName = extentionedName ;
	}
	@Override
	public boolean accept(File file) {
		if(file.isDirectory()){
			return true ;
		}
		String fileName = file.getName();
		int index = fileName.lastIndexOf(".");
		if(index>0 && index<fileName.length()-1){
			//impress that the file name doesn't contain such type :".xxx" or "xxx."
			String extention = fileName.substring(index+1).toLowerCase();
			
			if(extention.equals(extentionedName)){
				return true ;
			}
		}
		return false ;
	}

	@Override
	public String getDescription() {
		if(extentionedName.equals("java")){
			return "*.java";
		}
		if(extentionedName.equals("class")){
			return "*.java";
		}
		if(extentionedName.equals("txt")){
			return "*.txt";
		}
		return "" ;
	}

}
