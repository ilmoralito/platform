package ni.edu.ucc.leon

class CustomerActivity extends Activity {

    Customer customer

    static constraints = {
        customer nullable: false
    }

    static mapping = {
        version false
        table 'customer_activities'
    }
}
