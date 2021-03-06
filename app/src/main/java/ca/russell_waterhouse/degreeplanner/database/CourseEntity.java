package ca.russell_waterhouse.degreeplanner.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

@Entity (tableName = "Courses")
public class CourseEntity implements Serializable {

    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(name = "CourseName")
    private String courseName;
    @ColumnInfo(name="prereq1")
    private String prereq1Str;
    @ColumnInfo(name="prereq2")
    private String prereq2Str;
    @ColumnInfo(name="prereq3")
    private String prereq3Str;
    @ColumnInfo(name = "offeredInFall")
    private boolean offeredInFall;
    private boolean offeredInSpring;
    private boolean offeredInSummer;
    private boolean completed;
    private double numberGrade;
    private String letterGrade;
    private int scheduledYear;
    private char scheduledSemester;

    public CourseEntity(@NonNull String courseName, String prereq1Str, String prereq2Str, String prereq3Str, boolean offeredInFall, boolean offeredInSpring, boolean offeredInSummer, boolean completed, double numberGrade, String letterGrade, int scheduledYear, char scheduledSemester) {
        this.courseName = courseName;
        this.prereq1Str = prereq1Str;
        this.prereq2Str = prereq2Str;
        this.prereq3Str = prereq3Str;
        this.offeredInFall = offeredInFall;
        this.offeredInSpring = offeredInSpring;
        this.offeredInSummer = offeredInSummer;
        this.completed = completed;
        this.numberGrade = numberGrade;
        this.letterGrade = letterGrade;
        this.scheduledYear = scheduledYear;
        this.scheduledSemester = scheduledSemester;
    }

    @NonNull
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(@NonNull String courseName) {
        this.courseName = courseName;
    }

    public String getPrereq1Str() {
        return prereq1Str;
    }

    public void setPrereq1Str(String prereq1Str) {
        this.prereq1Str = prereq1Str;
    }

    public String getPrereq2Str() {
        return prereq2Str;
    }

    public void setPrereq2Str(String prereq2Str) {
        this.prereq2Str = prereq2Str;
    }

    public String getPrereq3Str() {
        return prereq3Str;
    }

    public void setPrereq3Str(String prereq3Str) {
        this.prereq3Str = prereq3Str;
    }

    public boolean isOfferedInFall() {
        return offeredInFall;
    }

    public void setOfferedInFall(boolean offeredInFall) {
        this.offeredInFall = offeredInFall;
    }

    public boolean isOfferedInSpring() {
        return offeredInSpring;
    }

    public void setOfferedInSpring(boolean offeredInSpring) {
        this.offeredInSpring = offeredInSpring;
    }

    public boolean isOfferedInSummer() {
        return offeredInSummer;
    }

    public void setOfferedInSummer(boolean offeredInSummer) {
        this.offeredInSummer = offeredInSummer;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public double getNumberGrade() {
        return numberGrade;
    }

    public void setNumberGrade(double numberGrade) {
        this.numberGrade = numberGrade;
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    public void setLetterGrade(String letterGrade) {
        this.letterGrade = letterGrade;
    }

    public int getScheduledYear() {
        return scheduledYear;
    }

    public void setScheduledYear(int scheduledYear) {
        this.scheduledYear = scheduledYear;
    }

    public char getScheduledSemester() {
        return scheduledSemester;
    }

    public void setScheduledSemester(char scheduledSemester) {
        this.scheduledSemester = scheduledSemester;
    }

    @Override
    @NonNull
    public String toString() {
        return "CourseEntity{" +
                "courseName='" + courseName + '\'' +
                ", prereq1Str='" + prereq1Str + '\'' +
                ", prereq2Str='" + prereq2Str + '\'' +
                ", prereq3Str='" + prereq3Str + '\'' +
                ", offeredInFall=" + offeredInFall +
                ", offeredInSpring=" + offeredInSpring +
                ", offeredInSummer=" + offeredInSummer +
                ", completed=" + completed +
                ", numberGrade=" + numberGrade +
                ", letterGrade='" + letterGrade + '\'' +
                ", scheduledYear=" + scheduledYear +
                ", scheduledSemester=" + scheduledSemester +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseEntity)) return false;
        CourseEntity that = (CourseEntity) o;
        return offeredInFall == that.offeredInFall &&
                offeredInSpring == that.offeredInSpring &&
                offeredInSummer == that.offeredInSummer &&
                completed == that.completed &&
                Double.compare(that.numberGrade, numberGrade) == 0 &&
                scheduledYear == that.scheduledYear &&
                scheduledSemester == that.scheduledSemester &&
                courseName.equals(that.courseName) &&
                Objects.equals(prereq1Str, that.prereq1Str) &&
                Objects.equals(prereq2Str, that.prereq2Str) &&
                Objects.equals(prereq3Str, that.prereq3Str) &&
                Objects.equals(letterGrade, that.letterGrade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseName, prereq1Str, prereq2Str, prereq3Str, offeredInFall, offeredInSpring, offeredInSummer, completed, numberGrade, letterGrade, scheduledYear, scheduledSemester);
    }
}
