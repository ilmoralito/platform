package ni.edu.ucc.leon

class AcademicActivity extends Activity {

    Employee confirmedBy
    Date confirmationDate
    Employee approvedBy
    Date approvalDate

    static constraints = {
        confirmedBy nullable: true
        confirmationDate nullable: true
        approvedBy nullable: true
        approvalDate nullable: true
    }
}
