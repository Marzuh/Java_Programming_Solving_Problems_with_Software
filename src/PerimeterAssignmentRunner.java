import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answerFileResource
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int result=0;
        for(Point totPt : s.getPoints())
        {
            result++;
        }
        return result;
    }

    public double getAverageLength(Shape s) {
        // Put code here

        return getPerimeter(s)/getNumPoints(s);
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double largestSide = 0;
        // Start wth prevPt = the last point
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt
            double currDist = prevPt.distance(currPt);
            if(currDist>largestSide)
            {
                largestSide=currDist;
            }
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        return largestSide;
    }

    public double getLargestX(Shape s) {
        // Put code here
        double largestX=0;
        for(Point currPt : s.getPoints())
        {
            if(largestX<currPt.getX())
            {
                largestX=currPt.getX();
            }
        }

        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        double largestPerimeter=0;
        /*FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        largestPerimeter = getPerimeter(s);*/
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            if(largestPerimeter<getPerimeter(s))
            {
                largestPerimeter=getPerimeter(s);
            }
        }
        return largestPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;    // replace this code
        double  largestPerimeter=0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            if(largestPerimeter<getPerimeter(s))
            {
                largestPerimeter=getPerimeter(s);
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        System.out.println("points = " + getNumPoints(s));
        System.out.println("averenge lengh = " + getAverageLength(s));
        System.out.println("largest side = " + getLargestSide(s));
        System.out.println("largest X = " + getLargestX(s));
    }

    public void testPerimeterMultipleFiles() {
        // Put code here
        double largestPer = getLargestPerimeterMultipleFiles();
        System.out.println("The largest perimeter in files = "+ largestPer);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        System.out.println("File name with longest perimeter :" + getFileWithLargestPerimeter());
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This methttps://www.coursera.org/learn/java-programming/discussions/weeks/1/threads/vUcjHikMQZyHIx4pDOGcPwhod prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        //pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }


}
