package by.gurinovich.webproject.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

    private ArrayBlockingQueue<ProxyConnection> connectionQueue;
    private final int POOL_SIZE = 20;
    private static AtomicBoolean instanceCreated = new AtomicBoolean();
    private static ConnectionPool instance = null;
    private static ReentrantLock lock = new ReentrantLock();

    public static ConnectionPool getInstance() {
        if (!instanceCreated.get()) {
            lock.lock();
            if (instance == null) {
                instance = new ConnectionPool();
                instanceCreated.set(true);
            }
            lock.unlock();
        }
        return instance;
    }


    private ConnectionPool() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connectionQueue = new ArrayBlockingQueue<>(POOL_SIZE);
            for (int i = 0; i < POOL_SIZE; i++) {
                ProxyConnection connection = new ProxyConnection(ConnectionDB.getConnection());
                connectionQueue.offer(connection);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage()+ e.getSQLState(), e);
        }
    }


    void releaseConnection(ProxyConnection connection) {
        try {
            if (connection.getAutoCommit()) {

                connectionQueue.put(connection);
            }
        } catch (InterruptedException | SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }


    }


    public ProxyConnection createConnection() {
        ProxyConnection connection = null;
        try {
            connection = connectionQueue.take();
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return connection;
    }


    public void destroyConnection() {
        for (int i = 0; i < POOL_SIZE; i++) {
            ProxyConnection connection;
            try {
                connection = connectionQueue.take();
                connection.closeConnection();
            } catch (InterruptedException | SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        try {
            DriverManager.deregisterDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
