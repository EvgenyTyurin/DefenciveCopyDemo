/**
 * Defensive copy of Host field in Connection class at constructor and getter
 */

public class DefenciveCopyDemo {

    public static void main(String[] args) {
        Host yandexHost = new Host("ya.ru");
        // We want this connection always be at ya.ru
        Connection yandexConnection = new Connection(yandexHost);
        System.out.println(yandexConnection);
        // Attack via yandexHost variable
        yandexHost.setAddress("google.com");
        System.out.println(yandexConnection); // ya.ru because of defencive copy in constructor
        // Attack via getter
        Host trickyHost = yandexConnection.getHost();
        trickyHost.setAddress("google.com");
        System.out.println(yandexConnection); // ya.ru because of defencive copy in getter
    }

    static class Connection {
        // we want this intact
        private final Host host;

        public Connection(Host host) {
            // defencive copy
            this.host = new Host(host.getAddress());
        }

        public Host getHost() {
            // defencive copy
            return new Host(host.getAddress());
        }

        @Override
        public String toString() {
            return "Connection{" +
                    "host=" + host +
                    '}';
        }
    }

    static class Host {
        private String address;

        public Host(String address) {
            this.address = address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAddress() {
            return address;
        }

        @Override
        public String toString() {
            return "Host{" +
                    "address='" + address + '\'' +
                    '}';
        }
    }

}
