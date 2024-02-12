package org.example.repository.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.model.redis.OngoingMatch;
import org.example.repository.config.HibernateConfig;
import org.example.repository.config.RedisConfig;
import org.example.util.Mapper;
import org.hibernate.Session;
import redis.clients.jedis.Jedis;

public class OngoingMatchRepository {

    public OngoingMatch save(OngoingMatch match) {
        try (Jedis jedis = RedisConfig.jedisPool.getResource()) {
            jedis.set("/ongoingMathes/1", Mapper.mapper.writeValueAsString(match));
            return match;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
