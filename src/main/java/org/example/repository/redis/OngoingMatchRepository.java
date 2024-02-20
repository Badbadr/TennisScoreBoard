package org.example.repository.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.model.redis.OngoingMatch;
import org.example.repository.config.RedisConfig;
import org.example.util.Mapper;
import redis.clients.jedis.Jedis;

import java.util.UUID;

public class OngoingMatchRepository {

    public OngoingMatch save(OngoingMatch match) {
        try (Jedis jedis = RedisConfig.jedisPool.getResource()) {
            jedis.set(match.getId().toString(), Mapper.mapper.writeValueAsString(match));
            return match;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public OngoingMatch get(UUID id) {
        try (Jedis jedis = RedisConfig.jedisPool.getResource()) {
            return Mapper.mapper.readValue(jedis.get(id.toString()), OngoingMatch.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            return null;
        }
    }

    public void delete(UUID id) {
        try (Jedis jedis = RedisConfig.jedisPool.getResource()) {
            jedis.del(id.toString());
        }
    }
}
